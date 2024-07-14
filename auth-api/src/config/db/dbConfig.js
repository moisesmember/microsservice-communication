import { Sequelize } from "sequelize";

const sequelize = new Sequelize("auth-db", "admin", "123456", {
    host: "localhost",
    dialect: "postgres",
    quoteIdentifiers: false,
    logging: false,
    pool: {
        max: 5,
        min: 0,
        acquire: 30000,
        idle: 10000
    },
    define: {
        syncOnAssociation: true,
        timestamps: false,
        underscored: true,
        underscoredALL: true
    }
});

sequelize
.authenticate().then(() => {
    console.info(`Connection has been stablished!`)
})
.catch(err => {
    console.error(`Unable to connect to the database: ${err}`)
    console.error(err.message)
})

export default sequelize;