document.addEventListener("DOMContentLoaded", function(e) {
    initTopicPage();
});

function initTopicPage() {
    loadTopics(selectedBrokerInfo());
}

function selectedBrokerInfo() {
    const select = document.getElementById('brokerSelect');
    const selectedOption = select.options[select.selectedIndex];

    const host = selectedOption.dataset.host;
    const port = selectedOption.dataset.port;

    return {
        host: host,
        port: port,
    }
}


function loadTopics(brokerInfo) {

    const fetchTopics = (brokerInfo) => {

        const query = new URLSearchParams(brokerInfo).toString();

        fetch(`/api/kafka/topics?${query}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                return;

                if (data != null) {
                    renderTopics(data);
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
            <tr>
                <td>${index + 1}</td>
                <td>${topic}</td>
                <td>
                    <button type="button" class="btn  btn-outline-secondary">Secondary</button>
                </td>
            </tr>
        `;
    }

    fetchTopics(brokerInfo);
}

function moveToRegisterTopic() {

    const form = document.createElement("form");
    form.method = "POST";
    form.action = "/kafka/topics/write";

    const brokerInfo = selectedBrokerInfo();

    const host = document.createElement("input");
    host.type = "hidden";
    host.name = "host";
    host.value = brokerInfo.host;

    const port = document.createElement("input");
    port.type = "hidden";
    port.name = "port";
    port.value = brokerInfo.port;

    document.body.appendChild(form); // 폼을 DOM에 추가
    form.appendChild(host);
    form.appendChild(port);
    form.submit();
}

