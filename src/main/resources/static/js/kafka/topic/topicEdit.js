function moveToTopicList() {

    const brokerInfo = {
        host: document.getElementById('host').value,
        port: document.getElementById('port').value,
    }

    const query = new URLSearchParams(brokerInfo).toString();

    location.href = `/kafka/topics?${query}`;
}



function editTopic() {
    const form = document.getElementById('editTopicForm');

    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }

    const topicEditInfo = {
        bootstrapServer: {
            host: form.querySelector('#host').value,
            port: form.querySelector('#port').value,
        },
        topicName: form.querySelector('#topicName').value,
        config: {
            retentionMs: form.querySelector('#retentionMs').value,
            retentionBytes: form.querySelector('#retentionBytes').value,
            cleanupPolicy: form.querySelector('#cleanupPolicy').value,
            maxMessageBytes: form.querySelector('#maxMessageBytes').value,
            segmentBytes: form.querySelector('#segmentBytes').value,
            segmentMs: form.querySelector('#segmentMs').value,
            minCleanableDirtyRatio: form.querySelector('#minCleanableDirtyRatio').value,
            deleteRetentionMs: form.querySelector('#deleteRetentionMs').value,
            flushMessages: form.querySelector('#flushMessages').value,
            flushMs: form.querySelector('#flushMs').value,
            messageTimestampType: form.querySelector('#messageTimestampType').value,
            compressionType: form.querySelector('#compressionType').value,
        }
    }

    const topicName = form.querySelector('#topicName').value;

    fetch(`/api/kafka/topics/${topicName}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(topicEditInfo),
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