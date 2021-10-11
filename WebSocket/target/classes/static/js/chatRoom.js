let send = document.getElementById("send");
let clear = document.getElementById("clear");

let showChatInfo = document.getElementById("showChatInfo");
let inputChatInfo = document.getElementById("inputChatInfo");

let username = document.getElementById("username");
let login = document.getElementById("login");
let socket = null;
let showOnlineUser = document.getElementById("showOnlineUser");
let onlineCount = document.getElementById("onlineCount");

clear.onclick = () => {
    inputChatInfo.value = null;
}

send.onclick = () => {
    if (inputChatInfo.value === "" || inputChatInfo.value === null) {
        alert("不能发送空信息！");
    } else {

        let p = document.createElement("p");
        let div = document.createElement("div");
        div.setAttribute("class", "sendDiv")
        p.innerHTML = inputChatInfo.value;
        p.setAttribute("class", "send_p");
        div.append(p);

        showChatInfo.appendChild(div);
        showChatInfo.scrollTop = showChatInfo.scrollHeight;

        let message = JSON.stringify({username: username.value, type: 0, info: inputChatInfo.value})
        socket.send(message);

        inputChatInfo.value = null;
    }
}

login.onclick = () => {
    login.disabled = true;
    username.disabled = true;
    if (username.value !== "" || username.value !== null) {

        if (typeof (WebSocket) === "undefined") {
            alert("此浏览器不支持WebSocket，无法获取聊天信息内容！");
        } else {

            let socketUrl = "http://120.27.213.163:20101/chat_room/websocket/" + username.value;
            socketUrl = socketUrl.replace("http", "ws");
            if (socket !== null) {
                socket.close();
                socket = null;
            }

            socket = new WebSocket(socketUrl);

            console.log("已开启websocket")

            socket.onmessage = (msg) => {

                let data = JSON.parse(msg.data);
                console.log(data)
                if (data.list !== undefined) {
                    onlineCount.innerHTML = "当前在线人数:" + data.list.length;
                    showOnlineUser.innerHTML = "";
                    for (let i = 0; i < data.list.length; i++) {
                        let li = document.createElement("li");
                        li.innerText = data.list[i].user_id;
                        showOnlineUser.append(li)
                        console.log(li.innerHTML)
                    }
                }

                if (data.username !== username.value) {

                    let div = document.createElement("div");
                    div.setAttribute("class", "receiveDiv")

                    let p = document.createElement("p");
                    p.setAttribute("class", "receive_p");
                    p.innerHTML = data.username + " 说:";

                    let p2 = document.createElement("p");
                    p2.setAttribute("class", "receive_p2");
                    p2.innerHTML = data.info;

                    div.append(p);
                    div.append(p2);

                    showChatInfo.append(div);
                }


            }
        }

        socket.onclose = ()=>{
            console.log("websocket已关闭！");
        }

        return;
    }
    alert("用户名不能为空！");
}

function nowTime() {//获取当前时间
    let now = new Date();
    let _month = (10 > (now.getMonth() + 1)) ? '0' + (now.getMonth() + 1) : now.getMonth() + 1;
    let _day = (10 > now.getDate()) ? '0' + now.getDate() : now.getDate();
    let _hour = (10 > now.getHours()) ? '0' + now.getHours() : now.getHours();
    let _minute = (10 > now.getMinutes()) ? '0' + now.getMinutes() : now.getMinutes();
    let _second = (10 > now.getSeconds()) ? '0' + now.getSeconds() : now.getSeconds();
    return now.getFullYear() + '-' + _month + '-' + _day + ' ' + _hour + ':' + _minute + ':' + _second;
}

sendTime();

function sendTime() {
    let hr = document.createElement("hr")
    let h1 = document.createElement("h5");
    h1.setAttribute("class", "timeSend");
    h1.innerText = nowTime();
    showChatInfo.append(h1);
    showChatInfo.append(hr);
}

setInterval(sendTime, 300000);