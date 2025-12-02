# Event Booking App

Dịch vụ backend Spring Boot cung cấp API đặt vé sự kiện với đầy đủ luồng xác thực, quản lý sự kiện, vé, thanh toán và nhắc nhở. Tài liệu này hướng dẫn bạn cài đặt, cấu hình và vận hành dự án một cách an toàn.

---

## 🌟 Tính năng chính
- Đăng ký/đăng nhập, cấp JWT và refresh context bảo mật.
- CRUD sự kiện, tìm kiếm theo thời gian/địa điểm.
- Đặt vé, quản lý thanh toán và tình trạng thanh toán.
- Quản lý nhắc nhở trước sự kiện, gửi thông báo email (stub).
- Tầng mapper/dto rõ ràng, validation ở cả request lẫn entity.
- Bộ test dịch vụ + controller mẫu bằng Spring Boot Test & Mockito.

---

## 🧱 Kiến trúc & Stack
- **Java 21**, **Spring Boot 3** (Web, Data JPA, Validation, Security, OAuth2 Resource Server).
- **MySQL** làm database chính.
- **MapStruct** cho mapping DTO ↔ entity.
- **Lombok** giảm boilerplate.
- **JWT (jjwt)** cho xác thực stateless.
- **BCrypt** cho hashing mật khẩu.

---

## ⚙️ Yêu cầu hệ thống
- JDK 21+
- Maven 3.9+
- MySQL 8.x đang chạy (local hoặc remote)
- Git

---

## 🚀 Bắt đầu nhanh

### 1. Clone dự án
```bash
git clone https://github.com/Loc1909/event-booking-app.git
cd event-booking-app
```

### 2. Cấu hình môi trường
1. Tạo file `src/main/resources/application-local.yaml` (hoặc dùng biến môi trường) với nội dung mẫu:
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/event_booking_app?createDatabaseIfNotExist=true
       username: <mysql_user>
       password: <mysql_password>
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true
     sql:
       init:
         mode: always

   jwt:
     secret: <64+ ky-tu-base64>
     exp-minutes: 60
   ```
2. Không commit thông tin nhạy cảm; thêm file vào `.gitignore`.
3. Có thể tạo `application.yaml.example` để chia sẻ template cho team.

### 3. Chuẩn bị database & dữ liệu mẫu
- Tạo schema rỗng (nếu chưa có): `CREATE DATABASE event_booking_app CHARACTER SET utf8mb4;`
- Toàn bộ bảng sẽ được Hibernate sinh (`ddl-auto=update`).
- Nếu cần data mẫu, chỉnh sửa và chạy `src/main/resources/import.sql` sau khi server khởi động lần đầu. Lưu ý update lại hash Bcrypt bằng tiện ích `PasswordHashGenerator`.

---

## 🔐 Biến môi trường & cấu hình profile

| Key | Mô tả | Ví dụ | Bắt buộc |
| --- | --- | --- | --- |
| `SPRING_PROFILES_ACTIVE` | Profile chạy (`local`, `dev`, `prod`, …) | `local` | ✅ |
| `SPRING_DATASOURCE_URL` | JDBC URL | `jdbc:mysql://localhost:3306/event_booking_app` | ✅ |
| `SPRING_DATASOURCE_USERNAME` | User MySQL | `event_user` | ✅ |
| `SPRING_DATASOURCE_PASSWORD` | Password MySQL | `secret` | ✅ |
| `JWT_SECRET` | Chuỗi Base64 tối thiểu 256-bit | `MTAwUGVy...` | ✅ |
| `JWT_EXP_MINUTES` | Thời gian sống access token | `60` | ➖ |
| `SPRING_SQL_INIT_MODE` | Điều khiển chạy `import.sql` | `always` / `never` | ➖ |
| `APP_SECURITY_ENABLED` | Cờ bật/tắt security (dev only) | `true` | ➖ |

> Quy ước: mỗi môi trường nên có file `application-{profile}.yaml` chỉ chứa **mặc định an toàn**, mọi secret lấy từ env/secret manager.

---

## ▶️ Chạy ứng dụng
```bash
mvn spring-boot:run
```
Hoặc build trước:
```bash
mvn clean package
java -jar target/event-booking-app-0.0.1-SNAPSHOT.jar
```

Ứng dụng sẽ lắng nghe tại `http://localhost:8080`.

---

## 🛡️ Bảo mật & JWT
- `SpringSecurityConfig` bật JWT filter, mỗi request (trừ `/api/auth/**`) phải gửi header `Authorization: Bearer <token>`.
- `jwt.secret` phải đủ dài (>=256 bit). Nên lưu ở secret manager hoặc biến môi trường (`JWT_SECRET`).
- Biến `app.security.enabled` chỉ nên dùng làm cờ debug; đảm bảo không vô tình tắt security ở môi trường thật.

---

## 📁 Cấu trúc thư mục đáng chú ý
| Đường dẫn | Mô tả |
| --- | --- |
| `src/main/java/com/eventbooking/controller` | REST controllers (Auth, Event, Booking, Ticket, Payment, User). |
| `src/main/java/com/eventbooking/service` | Business service + interface + implementation. |
| `src/main/java/com/eventbooking/dto` | DTO request/response theo module. |
| `src/main/java/com/eventbooking/security` | Filter JWT, config security. |
| `src/main/java/com/eventbooking/mapper` | MapStruct mappers. |
| `src/main/resources` | Config, seed SQL, template email. |
| `src/test/java` | Unit & integration tests cho controller/service. |

---

## 🗃️ Lược đồ dữ liệu

| Bảng | Trường chính | Liên kết |
| --- | --- | --- |
| `users` | `id`, `full_name`, `email`, `password`, `role` | 1-n với `bookings`, `tickets`, `reminders` |
| `events` | `id`, `title`, `description`, `location`, `start_time`, `end_time`, `capacity` | 1-n với `tickets`, `bookings` |
| `bookings` | `id`, `user_id`, `event_id`, `status`, `total_price` | liên kết tới `payments`, `tickets` |
| `tickets` | `id`, `booking_id`, `seat_number`, `qr_code_url` | quan hệ 1-1 với `payments` (tùy config) |
| `payments` | `id`, `booking_id`, `amount`, `status`, `provider`, `transaction_ref` | chứa log thanh toán |
| `reminders` | `id`, `user_id`, `event_id`, `offset_minutes`, `channel` | phục vụ cron/notification service |

Mặc định JPA sử dụng `@OneToMany` lazy load, mapper DTO xử lý chuyển đổi khi trả về client.

---

## 📡 API tổng quan

| Module | Endpoint chính |
| --- | --- |
| Auth | `POST /api/auth/register`, `POST /api/auth/login` |
| Users | `GET /api/users/me`, `PUT /api/users/{id}` |
| Events | `GET /api/events`, `POST /api/events`, `PUT /api/events/{id}`, `GET /api/events/{id}` |
| Bookings | `POST /api/bookings`, `GET /api/bookings/me`, `PATCH /api/bookings/{id}/status` |
| Tickets | `GET /api/tickets/{id}`, `GET /api/tickets/user/{userId}` |
| Payments | `POST /api/payments`, `GET /api/payments/{bookingId}` |
| Reminders | `POST /api/reminders`, `GET /api/reminders/user/{userId}` |

> Chi tiết schema xem trong các lớp DTO tương ứng.

---

## 📘 Tài liệu API chi tiết

### Quy ước chung
- **Auth header**: `Authorization: Bearer <jwt>`, trừ các endpoint public (đánh dấu `Public`).
- **Response chuẩn**: 
  ```json
  {
    "success": true/false,
    "data": { ... } | null,
    "message": "Optional description",
    "errors": [ ... ] // chỉ có nếu validate thất bại
  }
  ```
- **Pagination**: dùng `page`, `size`, `sort` theo chuẩn Spring Data (`/api/events?page=0&size=20&sort=startTime,asc`).

### Auth
| Method | Path | Auth | Request body | Response 2xx | Lỗi thường gặp |
| --- | --- | --- | --- | --- | --- |
| `POST` | `/api/auth/register` | Public | `RegisterRequest`<br>`{ fullName, email, password }` | `UserResponse` + auto-login token (tùy config) | `409 CONFLICT` email tồn tại, `400 BAD_REQUEST` validate |
| `POST` | `/api/auth/login` | Public | `LoginRequest`<br>`{ email, password }` | `{ accessToken, expiresIn, user }` | `401 UNAUTHORIZED` sai thông tin |
| `POST` | `/api/auth/logout` | JWT | Không body | `{ success: true }` (stateless nên chỉ để client clear token) | — |

### Users
| Method | Path | Auth | Body | Response |
| --- | --- | --- | --- | --- |
| `GET` | `/api/users/me` | JWT | — | Thông tin user hiện tại |
| `PUT` | `/api/users/{id}` | JWT (ROLE_USER tự cập nhật, ROLE_ADMIN cập nhật người khác) | `UpdateUserRequest` | User sau cập nhật |
| `GET` | `/api/users/{id}` | JWT (ROLE_ADMIN) | — | Chi tiết user |

### Events
| Method | Path | Auth | Body | Mô tả |
| --- | --- | --- | --- | --- |
| `GET` | `/api/events` | Public | Query optional: `keywords`, `city`, `dateFrom`, `dateTo`, `onlyAvailable` | Danh sách sự kiện + phân trang |
| `GET` | `/api/events/{id}` | Public | — | Chi tiết sự kiện |
| `POST` | `/api/events` | JWT (ROLE_ADMIN/ORGANIZER) | `CreateEventRequest` | Tạo mới sự kiện |
| `PUT` | `/api/events/{id}` | JWT (ROLE_ADMIN/ORGANIZER) | `UpdateEventRequest` | Cập nhật |
| `DELETE` | `/api/events/{id}` | JWT (ROLE_ADMIN) | — | 204 khi xóa thành công |

### Bookings & Tickets
| Method | Path | Auth | Body | Mô tả |
| --- | --- | --- | --- | --- |
| `POST` | `/api/bookings` | JWT | `{ eventId, tickets: [{seatType, quantity}], paymentMethod }` | Tạo booking ở trạng thái `PENDING_PAYMENT` |
| `GET` | `/api/bookings/me` | JWT | Query `status` | Danh sách booking của user |
| `PATCH` | `/api/bookings/{id}/status` | JWT (ROLE_ADMIN) | `{ status }` | Cập nhật trạng thái (`CONFIRMED`, `CANCELLED`, …) |
| `GET` | `/api/tickets/{id}` | JWT | — | Chi tiết vé (bao gồm QR/link) |
| `GET` | `/api/tickets/user/{userId}` | JWT (ROLE_ADMIN hoặc owner) | — | Tất cả vé theo user |

### Payments
| Method | Path | Auth | Body | Response |
| --- | --- | --- | --- | --- |
| `POST` | `/api/payments` | JWT | `{ bookingId, provider, amount, metadata }` | `PaymentResponse` với status `SUCCESS`/`FAILED` |
| `GET` | `/api/payments/{bookingId}` | JWT | — | Lịch sử thanh toán của booking |

### Reminders
| Method | Path | Auth | Body | Mô tả |
| --- | --- | --- | --- | --- |
| `POST` | `/api/reminders` | JWT | `{ eventId, offsetMinutes, channel }` | Đăng ký nhắc nhở |
| `GET` | `/api/reminders/user/{userId}` | JWT (ROLE_ADMIN hoặc owner) | — | Danh sách nhắc nhở |
| `DELETE` | `/api/reminders/{id}` | JWT | — | Hủy một nhắc nhở |

### Validation chính
- Các request có annotation Jakarta Validation (`@NotBlank`, `@Future`, `@RequireCoordinatesForNearby` custom).
- Khi vi phạm, `GlobalExceptionHandler` trả JSON dạng:
  ```json
  {
    "success": false,
    "message": "Validation failed",
    "errors": [
      { "field": "startTime", "reason": "must be in the future" }
    ]
  }
  ```

---

## 📨 Ví dụ request/response

**Đăng nhập**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{ "email": "user1@example.com", "password": "password123" }'
```
Response mẫu:
```json
{
  "success": true,
  "data": {
    "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "expiresIn": 3600,
    "user": {
      "id": 1,
      "fullName": "Nguyen Van A",
      "role": "USER"
    }
  }
}
```

**Tạo sự kiện**
```bash
curl -X POST http://localhost:8080/api/events \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
        "title": "Tech Conference",
        "location": "HCMC",
        "startTime": "2025-03-01T09:00:00Z",
        "endTime": "2025-03-01T18:00:00Z",
        "capacity": 200,
        "price": 99.0
      }'
```

---

## ✅ Kiểm thử & chất lượng
- Chạy toàn bộ test: `mvn clean test`
- Test service cụ thể: `mvn -Dtest=BookingServiceTest test`

---

## 📄 License
Phân phối dưới giấy phép MIT. Xem `LICENSE` để biết chi tiết.