import express from "express"
import * as db from "./src/config/db/seed.js"
import userRoutes from "./src/modules/user/routes/UserRouter.js";

const app = express();
const env = process.env;
const PORT = env.PORT || 8080;


db.createInitialData();

app.use(express.json());

app.get('/api/status', (req, res) => {
    return res.status(200).json({
        service: "Auth-API",
        status: "up",
        httpStatus: 200
    })
})

app.use(userRoutes);

app.listen(
    PORT, () => {
        console.info(`Server started successfully at port ${PORT}`)
    }
)