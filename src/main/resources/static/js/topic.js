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
            <tr data-name="${topic}">
                <td>${index + 1}</td>
                <td>${topic}</td>
                <td>
                    <button type="button" 
                            onclick="describeTopic('${topic}');" 
                            class="btn btn-icon btn-outline-secondary btn-sm">
                            <i class="fa-solid fa-circle-info"></i>
                            <span>describe</span>
                    </button>
                </td>
                <td>
                    <button type="button" class="btn btn-icon btn-outline-secondary btn-sm">
                        <i class="fa-solid fa-list-check"></i>
                        <span>record</span>
                    </button>
                </td>
                <td>
                    <button type="button" 
                            onclick="moveToEditTopic('${topic}');" 
                            class="btn btn-icon btn-outline-secondary btn-sm">
                        <i class="fa-regular fa-pen-to-square"></i>
                        <span>edit</span>
                    </button>
                    <button type="button" onclick="" class="btn  btn-outline-secondary btn-sm">
                        <i class="fa-solid fa-delete-left"></i>
                        <span>delete</span>
                    </button>
                </td>
            </tr>
        `;
    }

    clearTopics();
    fetchTopics(selectedBrokerInfo());
}

function moveToRegisterTopic() {

    const form = document.createElement("form");
    form.method = 'POST';
    form.action = '/kafka/topics/write';

    const brokerInfo = selectedBrokerInfo();

    const host = document.createElement("input");
    host.type = 'hidden';
    host.name = 'host';
    host.value = brokerInfo.host;

    const port = document.createElement("input");
    port.type = 'hidden';
    port.name = 'port';
    port.value = brokerInfo.port;

    document.body.appendChild(form);
    form.appendChild(host);
    form.appendChild(port);
    form.submit();
}

function moveToEditTopic(topicName) {
    const form = document.createElement("form");
    form.method = 'GET';
    form.action = `/kafka/topics/${topicName}/edit`;

    const brokerInfo = selectedBrokerInfo();

    const host = document.createElement("input");
    host.type = 'hidden';
    host.name = 'host';
    host.value = brokerInfo.host;

    const port = document.createElement("input");
    port.type = 'hidden';
    port.name = 'port';
    port.value = brokerInfo.port;

    document.body.appendChild(form);
    form.appendChild(host);
    form.appendChild(port);
    form.submit();
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

function describeTopic(topicName) {

    const fetchTopic = (topicName) => {

        const topicInfoRequest = {
            topicName: topicName,
            bootstrapServer: selectedBrokerInfo()
        }

        fetch(`/api/kafka/topics/${topicName}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(topicInfoRequest)
        })
            .then(response => response.json())
            .then(response => {
                if (response.isSuccess) {
                    setTopicInfo(response.data);
                }
            })
            .catch(error => {
                console.error('에러:', error);
            });
    }

    const setTopicInfo = (topicInfo) => {
        document.getElementById('describeTopicName').value = topicInfo.topicName;
        document.getElementById('describeTopicInternal').value = topicInfo.internal;

        const partitionSection = document.getElementById('describeTopicModal').querySelector('.describeTopicPartitionsDiv');
        partitionSection.innerHTML = '';
        topicInfo.partitions.forEach((partition) => {
            partitionSection.insertAdjacentHTML('beforeend', partitionUi(partition));
        })

    }

    const partitionUi = (partitionInfo) => {
        const partitionData = JSON.stringify({
            isr: partitionInfo.isr,
            leader: partitionInfo.leader,
            replicas: partitionInfo.replicas,
        }, null, 2);

        return `
            <div class="input-group">
                <span class="input-group-text">Partition ${partitionInfo.partition}</span>
                <textarea class="form-control" 
                          rows="7"
                          readonly
                          spellcheck="false">${partitionData}</textarea>
            </div>
        `;
    }

    fetchTopic(topicName);
    openModal('describeTopicModal');
}
