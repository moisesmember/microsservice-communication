import bcrypt from "bcrypt";
import jwt from "jsonwebtoken";
import userRepository from "../repository/UserRepository.js";
import * as httpStatus from "../../../config/constants/httpStatus.js";
import * as secrets from "../../../config/constants/secrets.js";
import UserException from "../exception/UserException.js";

class UserService {
    async findByEmail(req) {
        try {
            const { email } = req.params;
            const { authUser } = req;
            this.validateRequestData(email);
            let user = await userRepository.findByEmail(email);
            this.validateUserNotFound(user);
            this.validateAuthenticedUser(user, authUser);
            return {
                status: httpStatus.SUCCESS,
                user: {
                    id: user.id,
                    name: user.name,
                    email: user.email,
                },
            }
        }catch (err) {
            return {
                status: err.status ? err.status : httpStatus.INTERNAL_SERVER_ERROR,
                message: err.message,
            }
        }
    }

    validateRequestData(email) {
        if (!email) {
            throw new UserException(
                httpStatus.BAD_REQUEST,
                "Email is required");
        }
    }

    validateUserNotFound(user) {
        if (!user) {
            throw new UserException(
                httpStatus.BAD_REQUEST,
                "User not found");
        }
    }

    validateAuthenticedUser(user, authUser) {
        if (user.id !== authUser.id || !authUser) {
            throw new UserException(
                httpStatus.FORBIDDEN, 
                "You cannot see this user data.");
        }
    }

    async validatePassword(password, hashPassword) {
        if (!bcrypt.compareSync(password, hashPassword)) {
            throw new UserException(
                httpStatus.UNAUTHORIZED,
                "Password is incorrect"
            );
        }
    }

    async getAccessToken(req) {
        try {
            const { email, password } = req.body;
            this.validateAccessTokenData(email, password);
            let user = await userRepository.findByEmail(email);
            this.validateUserNotFound(user);
            await this.validatePassword(password, user.password);
            const authUser = {
                id: user.id,
                name: user.name,
                email: user.email,
            }
            const accessToken = jwt.sign({authUser}, secrets.API_SECRET, {expiresIn: '1d'});
            return {
                status: httpStatus.SUCCESS,
                accessToken
            };
        }catch (err) {
            return {
                status: err.status ? err.status : httpStatus.INTERNAL_SERVER_ERROR,
                message: err.message,
            }
        }
        
    }

    validateAccessTokenData(email, password) {
        if (!email && !password) {
            throw new UserException(
                httpStatus.UNAUTHORIZED, 
                "Email and passaword must be informed.");
        }
    }
}

export default new UserService();