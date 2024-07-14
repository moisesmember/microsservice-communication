import { Router } from "express";
import userController from "../controller/userController.js";

const router = new Router();

router.get('/api/user/email/:email', userController.findByEmail);

export default router;

