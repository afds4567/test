FROM node:18.10.0
// 나에게 맞는 node 버전을 적어준다.

RUN mkdir -p /app
WORKDIR /app
ADD . /app
// mkdir 로 /app 폴더를 만들어서
// 경로를 /app 으로 이동한 뒤에
// ADD 로 모든 파일을 /app 으로 복사한다

RUN npm install


ENV HOST 0.0.0.0
EXPOSE 3000
// 모든 ip 에서 접근 가능
// 3000 포트 개방 포트 여러개를 개방하려면, EXPOSE 3000 80 이렇게 띄워서 써주면 된다.


CMD ["npm", "start"]