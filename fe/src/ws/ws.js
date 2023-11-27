// 在你的前端代码中
const socket = new WebSocket('ws://localhost:8080/socket');  // 替换成实际的WebSocket地址

socket.onmessage = function (event) {
    const reminderMessage = JSON.parse(event.data);
    alert(`Reminder: ${reminderMessage.userId}, please return the borrowed items!`);
};

socket.onclose = function (event) {
    console.error('WebSocket closed:', event);
};