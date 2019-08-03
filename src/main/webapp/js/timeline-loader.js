/*
 * Copyright 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */




/**
 * Shows the message form if the user is logged in and viewing their own page.
 */
function showMessageFormIfViewingSelf() {
    fetch('/login-status')
        .then((response) => {
            return response.json();
        })
        .then((loginStatus) => {
            if (loginStatus.isLoggedIn) {
                const messageForm = document.getElementById('message-form');
                messageForm.classList.remove('hidden');
            }
            else
            {
                window.location.replace('/');
            }
        });
}

/** Fetches messages and add them to the page. */
function fetchMessages() {
    const url = '/timeline';
    fetch(url)
        .then((response) => {
            return response.json();
        })
        .then((messages) => {
            const messagesContainer = document.getElementById('message-container');
            if (messages.length == 0) {
                messagesContainer.innerHTML = '<p>This user has no posts yet.</p>';
            } else {
                messagesContainer.innerHTML = '';
            }
            messages.forEach((message) => {
                const messageDiv = buildMessageDiv(message);
                messagesContainer.appendChild(messageDiv);
            });
        });
}

/**
 * Builds an element that displays the message.
 * @param {Message} message
 * @return {Element}
 */
function buildMessageDiv(message) {
    /*const headerDiv = document.createElement('div');
    headerDiv.classList.add('message-header');
    headerDiv.appendChild(document.createTextNode(
        message.user + ' - ' + new Date(message.timestamp)));

    const bodyDiv = document.createElement('div');
    bodyDiv.classList.add('message-body');
    bodyDiv.innerHTML = message.text;

    const messageDiv = document.createElement('div');
    messageDiv.classList.add('message-div');
    messageDiv.appendChild(headerDiv);
    messageDiv.appendChild(bodyDiv);
    */
    let currentTime = new Date();
    let messageTime = new Date(message.timestamp);
    let messageText;
    if(currentTime >= messageTime)
    {
        messageText = "Visited at";
    }
    else if(currentTime <= messageTime)
    {
        messageText = "Will be Visiting To";
    }
    const messageDiv = document.createElement('div');
    messageDiv.classList.add('qa-message-list');
    messageDiv.innerHTML = ' <div class="message-item" id="m1">\n' +
        '            <div class="message-inner">\n' +
        '                <div class="message-head clearfix">\n' +
        '                    <div class="avatar pull-left"><a href="./index.php?qa=user&qa_1=admin"><img src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png"></a></div>\n' +
        '                    <div class="user-detail">\n' +
        '                        <h5 class="handle">'+message.user +'</h5>\n' +
        '                        <div class="post-meta">\n' +
        '                            <div class="asker-meta">\n' +
        '                                <span class="qa-message-what"></span>\n' +
        '                                <span class="qa-message-when">\n' +
        '\t\t\t\t\t\t\t\t\t\t\t\t<span class="qa-message-when-data">'+ new Date(message.timestamp)+'</span>\n' +
        '\t\t\t\t\t\t\t\t\t\t\t</span>\n' +
        '                                <span class="qa-message-who">\n' +
        '\t\t\t\t\t\t\t\t\t\t\t\t<span class="qa-message-who-pad">by </span>\n' +
        '\t\t\t\t\t\t\t\t\t\t\t\t<span class="qa-message-who-data"><a href="./index.php?qa=user&qa_1=admin">'+message.user +'</a></span>\n' +
        '\t\t\t\t\t\t\t\t\t\t\t</span>\n' +
        '\t\t\t\t\t\t\t\t\t\t\t\t<span class="qa-message-who-pad">'+messageText+'</span>\n' +
        '\t\t\t\t\t\t\t\t\t\t\t\t<span class="qa-message-who-data"><a href="">'+message.city +'</a></span>\n' +
        '\t\t\t\t\t\t\t\t\t\t\t</span>\n' +
        '                            </div>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '                <div class="qa-message-content">\n' +
        message.text+
        '                </div>\n' +
        '            </div></div>';
    return messageDiv;
}

/** Fetches data and populates the UI of the page. */
function buildUI() {

    showMessageFormIfViewingSelf();
    fetchMessages();
}