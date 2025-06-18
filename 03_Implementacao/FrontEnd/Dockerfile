# Use official Node.js LTS image
FROM node:alpine

RUN apk update

RUN apk add vim

RUN apk add nano

# Set working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json
COPY package.json package-lock.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application files
COPY . .

# Expose the application port
EXPOSE 80

# Start the application
CMD ["node", "index.js"]