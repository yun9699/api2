select * from tbl_product;

insert into tbl_product (pname, price, status)
values ('Product',4500,0);

update tbl_product set  pname = concat(pname,pno)
where pno > 0;

select * from tbl_review;

insert into tbl_review (reviewer, score, product_pno)
values ('r1',4,15);

insert into tbl_review (reviewer, score, product_pno)
values ('r2',4,13);

insert into tbl_review (reviewer, score, product_pno)
values ('r3',4,11);

select
p1_0.pno,
p1_0.pname,
p1_0.price,
count(r1_0.rno),
coalesce(avg(r1_0.score), 0.0)
from
tbl_product p1_0
left join
tbl_review r1_0
on r1_0.product_pno=p1_0.pno
group by
p1_0.pno
order by
p1_0.pno desc
limit
0, 10


단일 클래스 (엔티티)
- C,R,U,D

- Querydsl 동적쿼리

- Projections
- @Query에서 사용하는 법
- Querydsl에서 select를 해서 사용하는 법

연관관계 - ManyToOne
- 단방향 참조
- C
- R
- Querydsl
- left join - group by

VO - @ElementCollection
- @EntityGraph
- Querydsl에서 사용법
- @Bathsize , fetchJoin() - limit