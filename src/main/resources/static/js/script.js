document.addEventListener("DOMContentLoaded", function () {
    const socket = new SockJS('/ws'); // Conectar al endpoint WebSocket
    const stompClient = Stomp.over(socket);

    let usuarioReceptor = document.querySelector("#username").value;
    let mensajesDiv = document.querySelector("#messages");

    stompClient.connect({}, function (frame) {
        console.log('Conectado: ' + frame);

        stompClient.subscribe('/user/queue/messages', function (message) {
            const messages = document.getElementById('messages');
            const li = document.createElement('li');
            const mensaje = JSON.parse(message.body);
            li.textContent = mensaje.usuarioEmisor + ": " + mensaje.texto;
            messages.appendChild(li);
        });
    });

    document.getElementById("messageForm").addEventListener("submit", function (event) {
        event.preventDefault();

        const messageInput = document.getElementById("message");
        const mensaje = {
            usuarioReceptor: usuarioReceptor,
            texto: messageInput.value,
            fecha: new Date().toISOString()
        };

        const mensajeDiv = document.createElement('div');

        mensajeDiv.classList.add('mensaje-emisor');

        mensajeDiv.innerHTML = `<p>${messageInput.value}</p>`;
        mensajesDiv.appendChild(mensajeDiv);

        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(mensaje));
        messageInput.value = '';
    });
});