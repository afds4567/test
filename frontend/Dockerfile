FROM node:18.10.0 as build

RUN mkdir -p /app
WORKDIR /app
ADD . /app

RUN npm install --force
RUN npm run build


FROM nginx

COPY --from=build /app/dist /usr/share/nginx/html/

# Add nginx.conf
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
