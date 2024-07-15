FROM node:16-alpine
WORKDIR .
COPY package*.json ./
# RUN apk add --no-cache make g++ python3
RUN yarn
# RUN yarn add bcrypt@^5.1.1 --force-rebuild
COPY . .
EXPOSE 8080
CMD [ "node", "app.js" ]