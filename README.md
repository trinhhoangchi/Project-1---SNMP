MIB-2 Browser

Tác giả:

Nguyễn Quang Huy

Trịnh Hoàng Chi

Tổng quan

MIB-2 Browser là một ứng dụng đồ họa dựa trên Java, giúp người dùng khám phá và truy xuất thông tin từ các thiết bị hỗ trợ SNMP sử dụng MIB (Management Information Base). Giao diện được xây dựng bằng Java Swing, tập trung vào tính đơn giản và hiệu quả.

Phần mềm được thiết kế để chạy trên Visual Studio Code (VS Code).

Tính năng chính

Duyệt cây MIB:

Xem cấu trúc MIB dưới dạng cây thư mục.

Hiển thị thông tin chi tiết của từng nút, bao gồm OID, cú pháp và mô tả.

Thao tác SNMP:

Thực hiện SNMP GET để lấy giá trị của một OID cụ thể.

Thực hiện SNMP WALK để lấy tất cả OID con của một OID gốc.

Hiển thị động:

Hiển thị dữ liệu truy xuất được dưới dạng bảng.

Hướng dẫn sử dụng

Clone dự án:

git clone <repository-url>
cd <repository-folder>

Cài đặt VS Code:

Mở thư mục dự án trong VS Code.

Cài đặt "Java Extension Pack" nếu chưa có.

Biên dịch và chạy:

Dùng terminal trong VS Code:

javac -d bin src/*.java
java -cp bin Main

Hướng dẫn sử dụng:

Nhập IP Address, OID, và Community String.

Sử dụng nút Walk hoặc Get để lấy dữ liệu SNMP.

Duyệt cây MIB để xem thông tin chi tiết.

Cấu trúc thư mục

MIB-2-Browser/
|-- src/
|   |-- MIBBrowser.java         # Giao diện chính và logic ứng dụng
|   |-- Main.java               # Điểm khởi đầu của ứng dụng
|   |-- MIBTree.java            # Tạo cấu trúc cây MIB
|   |-- SnmpResult.java         # Mô hình dữ liệu cho kết quả SNMP
|   |-- TextAreaRenderer.java   # Hiển thị ô bảng với nhiều dòng
|   |-- MIBInfoDisplay.java     # Hiển thị thông tin nút MIB
|
|-- README.md                   # Tài liệu dự án

Giới hạn

Chỉ hỗ trợ SNMP phiên bản 1 và 2c.

Cần cấu hình thủ công cây MIB, chưa hỗ trợ tải động các tệp MIB bên ngoài.

Giả định thiết bị SNMP khả dụng và được cấu hình đúng.

Đóng góp

Hoan nghênh đóng góp! Các bước thực hiện:

Fork dự án.

Tạo nhánh mới (git checkout -b feature-name).

Commit thay đổi (git commit -m 'Add some feature').

Push nhánh (git push origin feature-name).

Tạo pull request.

Giấy phép

Dự án này được cấp phép theo MIT License. Xem tệp LICENSE để biết thêm chi tiết.

Lời cảm ơn

Chân thành cảm ơn thầy Nguyễn Quốc Khánh đã hướng dẫn thực hiện dự án.

Tài liệu tham khảo:

Java Swing Documentation

SNMP Protocol Overview
