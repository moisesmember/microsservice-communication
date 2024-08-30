import Order from "../modules/sales/model/Order.js";

export async function createData() {
    await Order.collection.drop();
    await Order.create(
        {
            products: [
                {productId: 1, quantity: 10},
                {productId: 2, quantity: 2},
                {productId: 3, quantity: 7},
            ],
            user: {
                id: 'a12jdjfhfififf',
                name: 'User Test',
                email: 'Usertest@gmail.com'
            },
            status: 'APPROVED',
            createdAt: new Date(),
            updatedAt: new Date()
        }
    );
    await Order.create(
        {
            products: [
                {productId: 1001, quantity: 10},
                {productId: 1002, quantity: 52},
            ],
            user: {
                id: 'a12jdjfhdffsdfiff',
                name: 'User Test 2',
                email: 'Usertest2@gmail.com'
            },
            status: 'APPROVED',
            createdAt: new Date(),
            updatedAt: new Date()
        }
    );
}