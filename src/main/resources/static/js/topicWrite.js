function moveToTopicList() {

    const brokerInfo = {
        host: document.getElementById('host').value,
        port: document.getElementById('port').value,
    }

    const query = new URLSearchParams(brokerInfo).toString();

    location.href = `/kafka/topics?${query}`;
}

function saveTopic() {
    const form = document.getElementById('registerTopicForm');

    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }

    const createTopicInfo = {
        bootstrapServer: {
            host: form.querySelector('#host').value,
            port: form.querySelector('#port').value,
        },
        topicName: form.querySelector('#topicName').value,
        partitions: form.querySelector('#partitions').value,
        replicationFactor: form.querySelector('#replicationFactor').value,
    }

    fetch('/api/kafka/topics', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(createTopicInfo),
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            if (data.isSuccess) {
                moveToTopicList();
            }
        })
        .catch(error => {
            console.error('에러:', error);
        });
}