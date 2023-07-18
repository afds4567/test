import { rest } from 'msw';

export const handlers = [
  // 포스트 목록
  rest.get('/pin/:id', (req, res, ctx) => {
    const pinId = req.params.id;

    return res(
      ctx.status(200),
      ctx.json({
        id: pinId,
        name: '오또상스시',
        address: '서울특별시 강남구 역삼동 123-456',
        description:
          '초밥을 파는 곳입니다. 점심 특선 있고 초밥 질이 괜찮습니다. 가격대도 다른 곳에 비해서 양호한 편이고 적당히 생각날 때 가면 좋을 것 같습니다.',
        latitude: '핀 위도',
        longtitude: '핀 경도',
        updatedAt: '2023-07-12',
      }),
    );
  }),
];