document.addEventListener("DOMContentLoaded", function(e) {
    initTopicPage();
});

function initTopicPage() {
    loadTopics();
}

const clearTopics = () => {
    document.getElementById('topicTable').innerHTML = '';
}

function selectedBrokerInfo() {
    const select = document.getElementById('brokerSelect');
    const selectedOption = select.options[select.selectedIndex];

    const host = selectedOption.dataset.host;
    const port = selectedOption.dataset.port;

    return {
        host: host,
        port: Number.parseInt(port),
    }
}


function loadTopics() {

    const fetchTopics = (brokerInfo) => {
        const query = new URLSearchParams(brokerInfo).toString();

        fetch(`/api/kafka/topics?${query}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(response => {
                clearTopics();
                if (response.isSuccess) {
                    renderTopics(response.data);
                }
            })
            .catch(error => {
                console.error('에러:', error);
            });
    }

    const renderTopics = (topics) => {
        document.getElementById('topicTable').innerHTML =
            topics.map((topic, index) => topicUi(topic, index))
                .join('');
    }

    const topicUi = (topic, index) => {
        return `
            <tr data-name="${topic.topicName}">
                <td>${index + 1}</td>
                <td>${topic.topicName}</td>
                <td>
                    <button type="button" 
                            onclick="sendMessage('${topic.topicName}');" 
                            class="btn btn-icon btn-outline-secondary btn-sm">
                            <i class="fa-solid fa-paper-plane"></i>
                            <span>send</span>
                    </button>
                </td>
            </tr>
        `;
    }

    clearTopics();
    fetchTopics(selectedBrokerInfo());
}

function selectedBrokerInfo() {
    const select = document.getElementById('brokerSelect');
    const selectedOption = select.options[select.selectedIndex];

    const host = selectedOption.dataset.host;
    const port = selectedOption.dataset.port;

    return {
        host: host,
        port: Number.parseInt(port),
    }
}

function filterTopics(filterInput) {
    const keyword = filterInput.value.toLowerCase();
    const topics = Array.from(document.getElementById('topicTable').querySelectorAll('tr'));

    if (keyword === "") {
        topics.forEach(topic => {
            topic.style.display = 'table-row';
        });
        return;
    }

    topics.forEach(topic => {
        const topicName = topic.dataset.name;

        if (topicName.toLowerCase().indexOf(keyword) === -1) {
            topic.style.display = 'none';
        } else {
            topic.style.display = 'table-row';
        }
    });
}

function sendMessage(topicName) {
    document.getElementById('receiveTopicName').value = topicName;
    openModal('sendMessageModal');
}

function send() {

    const messageRequest = {
        bootstrapServer: selectedBrokerInfo(),
        topicName: document.getElementById('receiveTopicName').value,
        key: document.getElementById('messageKey').value,
        message: document.getElementById('messageValue').value,
    }

    fetch(`/api/kafka/publish`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(messageRequest),
    })
        .then(response => response.json())
        .then(response => {
            closeModal();
        })
        .catch(error => {
            console.error('에러:', error);
        });
}