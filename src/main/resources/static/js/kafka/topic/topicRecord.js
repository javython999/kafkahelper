document.addEventListener("DOMContentLoaded", () => {
    connectSse();
    //initScroll();
});

function initScroll() {
    const myElement = document.querySelector('.newRecords');
    new SimpleBar(myElement, { autoHide: true });
}

function connectSse() {

    const topicName = document.getElementById('topicName').value;
    const host = document.getElementById('host').value;
    const port = document.getElementById('port').value;
    const eventSource = new EventSource(`/kafka/sse/subscribe/${topicName}?host=${host}&port=${port}`);

    eventSource.onmessage = function(event) {
        renderRecord(JSON.parse(event.data));
        scrollToLast();
    };

    eventSource.onopen = function() {
        console.log("SSE 연결 열림");
    };

    eventSource.onerror = function(error) {
        console.error("SSE 오류 발생", error);
        if (eventSource.readyState === EventSource.CLOSED) {
            console.log("SSE 연결 종료됨");
        }
    };
}

function renderRecord(record) {
    const recordList = document.querySelector('.simplebar-content');
    recordList.insertAdjacentHTML('beforeend', recordUi(record));
}

function recordUi(record) {
    return `
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-text">Key</span>
                <input class="form-control" value="${record.key}" readonly>
                <span class="input-group-text">Value</span>
                <input class="form-control" type="text" value="${record.message}" readonly>
                <span class="input-group-text">timestamp</span>
                <input class="form-control" type="text" value="${record.timestamp}" readonly>
            </div>
        </div>
    `;
}

function scrollToLast() {
    const container = document.querySelector('.newRecords');
    const simplebarInstance = SimpleBar.instances.get(container);
    simplebarInstance.getScrollElement().scrollTop = simplebarInstance.getScrollElement().scrollHeight;
}

function moveToTopicList() {

    const brokerInfo = {
        host: document.getElementById('host').value,
        port: document.getElementById('port').value,
    }

    const query = new URLSearchParams(brokerInfo).toString();

    location.href = `/kafka/topics?${query}`;
}
