<#import "/spring.ftl" as spring />
<#import "ui.ftl" as ui/>
<#assign titletext><@spring.message 'chat.title'/></#assign>
<@ui.headerWithjQuery title="👤 ${titletext} ${movie.title}"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.0/sockjs.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
    <style>
        .bubbleWrapper {
            padding: 10px 10px;
            display: flex;
            justify-content: flex-end;
            flex-direction: column;
            align-self: flex-end;
            color: #fff;
            overflow-x: auto;
        }
        .inlineContainer {
            display: inline-flex;
        }
        .inlineContainer.own {
            flex-direction: row-reverse;
        }
        .inlineIcon {
            width:20px;
            object-fit: contain;
        }
        .ownBubble {
            min-width: 60px;
            max-width: 700px;
            padding: 14px 18px;
            margin: 6px 8px;
            background-color: #5b5377;
            border-radius: 16px 16px 0 16px;
            border: 1px solid #443f56;
        }
        .otherBubble {
            min-width: 60px;
            max-width: 700px;
            padding: 14px 18px;
            margin: 6px 8px;
            background-color: #6C8EA4;
            border-radius: 16px 16px 16px 0;
            border: 1px solid #54788e;
        }
        .own {
            align-self: flex-end;
        }
        .other {
            align-self: flex-start;
        }
        span.own,
        span.other{
            font-size: 14px;
            color: grey;
        }
        label.chat {
            font-size: large;
        }
        div.chat {
            max-width: 1000px;
            max-height: 500px;
            margin: auto;
            border-radius: 16px;
            background-color: #CC2CA7
        }

        div.innerChat{
            min-width: 1000px;
            max-width: 1200px;
            max-height: 500px;
            margin: auto;
            overflow: auto;
            scrollbar-arrow-color: #CC2CA7;
            scrollbar-base-color: #5b5377;
            scrollbar-face-color: #5b5377;
            border-radius: 16px;
        }
    </style>

<script type="text/javascript">
    var stompClient = null;

    function setConnected(connected) {
        if (connected){
            document.getElementById('conBtn').style.visibility = 'hidden'
            document.getElementById('snd').style.visibility = 'visible'
            document.getElementById('chatDiv').style.visibility = 'visible'
        }else{
            document.getElementById('conBtn').style.visibility = 'visible'
        }
        document.getElementById('response').innerHTML = '';
    }

    function connect() {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            setConnected(true);
            console.log('Connected msg: ' + frame);
            stompClient.subscribe('/films', function(message) {
                var json = JSON.parse(message.body);
                showMessageOutput(json);
                var objDiv = document.getElementById("messageArea");
                objDiv.scrollTop = objDiv.scrollHeight;
            });
        });
    }

    function checkForEnterKey(){
        if (event.keyCode === 13) {
            event.preventDefault();
            document.getElementById("sendMessage").click();
        }
    }

    function send() {
        if (document.getElementById('chatMsg').value.length < 1)
            return;
        var message = {
            sender: "${user.username}",
            text: document.getElementById('chatMsg').value,
            film_id:  ${movie.id},
            email: "${user.email}"
        };
        document.getElementById('chatMsg').value = "";
        stompClient.send("/app/films/${movie.id}/chat", {}, JSON.stringify(message));

    }

    function showMessageOutput(message) {
        var messageElement = document.createElement('div');
        messageElement.className = 'bubbleWrapper';

        var container = document.createElement('div');
        container.className = (message.author.username === "${user.username}") ? 'inlineContainer own' : 'inlineContainer';

        var usernameText = document.createElement('div');
        usernameText.textContent = message.author.username;
        usernameText.style.color = "#d5a037"
        if (message.author.username === "${user.username}")
            usernameText.hidden = true;

        var UUID = message.uuid;
        var img = new Image();
        img.style.borderRadius = "50%"
        img.src =  message.uuid === null ? "https://html-online.com/editor/images/html-editor.png" : "../../images/" + UUID;
        img.style.height = '50px';
        img.style.width = '50px';
        if (message.author.username === "${user.username}")
            img.hidden = true;

        var textElement = document.createElement('div');
        textElement.className = (message.author.username === "${user.username}") ? 'ownBubble own' : 'otherBubble other';
        textElement.textContent += message.text;

        messageArea.appendChild(messageElement);
        messageElement.appendChild(container);
        container.appendChild(img);
        container.appendChild(textElement);
        textElement.insertBefore(usernameText, textElement.firstChild)
    }

    function printHistory(messageSender, messageText) {
        var messageElement = document.createElement('div');
        messageElement.className = 'bubbleWrapper';
        var container = document.createElement('div');
        container.className = (messageSender === "${user.username}") ? 'inlineContainer own' : 'inlineContainer';

        var usernameText = document.createElement('div');
        usernameText.textContent = messageSender;
        usernameText.style.color = "#d5a037"
        if (messageSender === "${user.username}")
            usernameText.hidden = true;

        var UUID = "https://html-online.com/editor/images/html-editor.png"
        var img = new Image();
        img.src = UUID;
        img.style.height = '50px';
        img.style.width = '50px';
        if (messageSender === "${user.username}")
            img.hidden = true;

        var textElement = document.createElement('div');
        textElement.className = (messageSender === "${user.username}") ? 'ownBubble own' : 'otherBubble other';
        textElement.textContent = messageText;

        messageArea.appendChild(messageElement);
        messageElement.appendChild(container);
        container.appendChild(img);
        container.appendChild(textElement);
        textElement.insertBefore(usernameText, textElement.firstChild)

    }



</script>
    <body>
    <div  class="w3-panel" style="display:flex;flex-direction: column;justify-content: center; align-items: center; padding: 10px; margin: 10px;">
        <div class="chatTitle">
            ${movie.description}
        </div>
    </div>
    <div class="container" style="display:flex;flex-direction: column;justify-content: center; align-items: center; padding: 10px; margin: 10px;" id="connFrm">
        <table >
            <tr>
                <td>
                    <button type="Submit" id="conBtn" onclick="connect()" class="btn" >Connect </button>
                    <button type="Submit" id="prfBtn"  onclick="location.href='/profile'" class="btn" >Profile </button>
                </td>
            </tr>
        </table>
    </div>

    <div id="chatDiv" style="display:flex;flex-direction: column;justify-content: center; align-items: center; padding: 10px; margin: 10px; visibility: hidden">
        <div id="chat-page" class="chat">
            <div id="messageArea" class="innerChat"></div>
        </div>
        <#list history as message>
            <script>
                printHistory("${message['author'].username}", "${message.text}");
            </script>
        </#list>
        <br/>
        <div id="snd" style="display:flex;flex-direction: column;justify-content: center; align-items: center; padding: 10px; margin: 10px; visibility: hidden">
            <input type="text" id="chatMsg" onkeyup="checkForEnterKey(this.value)" placeholder="Write message" required/>
            <button id="sendMessage" onclick="send();">Send</button>
        </div>
        <br/>
        <p id="response"></p>
    </div>


    </body>

<@ui.tail/>