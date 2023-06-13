<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Countdown</title>
    <style>
        .countdown-container {
            font-size: 24px;
            text-align: center;
            margin-top: 100px;
        }
    </style>
</head>
<body>

<div class="countdown-container">
    <p>Đang chuyển hướng đến trang chủ sau:</p>
    <div id="countdown"></div>
</div>

<script>
    var countDown = 5; // Đếm ngược từ 5
    var countdownElement = document.getElementById("countdown");

    
    
    function startCountdown() {
        countdownElement.innerHTML = countDown; // Hiển thị giá trị ban đầu

        var countdownInterval = setInterval(function() {
            countDown--; // Giảm giá trị đếm ngược

            if (countDown >= 0) {
                countdownElement.innerHTML = countDown; // Cập nhật giá trị đếm ngược
            } else {
                clearInterval(countdownInterval); // Dừng đếm ngược
                window.location.href = "index.jsp"; // Chuyển hướng đến trang index
            }
        }, 1000); // Mỗi 1000 milliseconds = 1 giây
    }

    // Gọi hàm startCountdown khi trang được tải
    window.onload = startCountdown;
</script>

</body>
</html>
