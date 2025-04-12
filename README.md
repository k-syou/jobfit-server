## 잡핏 백엔드 서버

### Getting Start

```shell
docker compose up -d
```

---

### Project Architecture

본 프로젝트는 레이어드 + 클린 아키텍처를 기반으로 설계되었습니다.

interfaces -> service -> domain <- infras

- interfaces layer
  - interfaces layer는 외부의 입출력을 담당하는 레이어입니다.
- service layer
  - service layer는 비즈니스 로직을 담당하는 레이어입니다.
- domain layer
  - domain layer는 JPA Entity 및 Repository 인터페이스를 담당하는 레이어입니다.
- infras layer
  - infras layer는 domain layer에 Repository 인터페이를 구현한 구현체를 담당하는 레이어입니다.

상위레이어는 하위레이어를 참조할 수 있고 하위레이어는 상위레이어를 참조할 수 없습니다.

Repository의 인터페이스는 꼭 domain layer에 만들고 구현체를 infras layer에 구현해서 사용해주세요. (DIP를 지켜주세요)

---
