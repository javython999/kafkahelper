document.addEventListener("DOMContentLoaded", () => {
    initBrokerPage();
});

function initBrokerPage() {
    loadBrokers();
}

function loadBrokers() {

    const fetchBrokers = async () => {
        fetch('/api/kafka/brokers', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(data => {
                if (data != null) {
                    renderBrokers(data);
                }
            })
            .catch(error => {
                console.error('에러:', error);
            });
    }

    const renderBrokers = (brokers) => {
        document.getElementById('brokerTable').innerHTML =
            brokers.map((broker, index) => brokerUi(broker, index+1))
                .join('');
    }

    const brokerUi = (broker, index) => {
        return `
            <tr>
                <td>${index}</td>
                <td>${broker.alias}</td>
                <td>${broker.bootstrapServer.host}</td>
                <td>${broker.bootstrapServer.port}</td>
                <td>
                    <button type="button" 
                            onclick="editBroker(${broker.id})"
                            class="btn btn-icon btn-outline-secondary btn-sm">
                        <i class="fa-regular fa-pen-to-square"></i>
                        <span>edit</span>
                    </button>
                    <button type="button" 
                            onclick="deleteBroker('${broker.alias}', ${broker.id})"
                            class="btn  btn-outline-secondary btn-sm">
                        <i class="fa-solid fa-delete-left"></i>
                        <span>delete</span>
                    </button>
                </td>
            </tr>
        `;
    }

    fetchBrokers();
}

function saveBroker() {

    const getUserInput = () => {
        const registerModal = document.getElementById('registerBrokerModal');
        const alias = registerModal.querySelector('#registerBrokerAlias').value;
        const host = registerModal.querySelector('#registerBrokerHost').value;
        const port = registerModal.querySelector('#registerBrokerPort').value;

        return {
            alias: alias,
            bootstrapServer: {
                host: host,
                port: port,
            },
        };
    }

    const validUserInput = (userInputData) => {

        if (userInputData.alias === '') {
            return false;
        }

        if (userInputData.host === '') {
            return false;
        }

        if (userInputData.port === '') {
            return false;
        }

        return true;
    }

    const fetchSaveBroker = (saveBroker) => {
        fetch('/api/kafka/brokers', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(saveBroker),
        })
            .then(response => response.json())
            .then(data => {
                if (data.id != null) {
                    loadBrokers();
                    clearModalInputs('registerBrokerModal');
                    closeModal();
                }
            })
            .catch(error => {
                console.error('에러:', error);
            });
    }

    const newBroker = getUserInput();
    if (validUserInput(newBroker)) {
        fetchSaveBroker(newBroker);
    } else {
        Alert.error("모든 항목을 입력해주시기 바랍니다.");
    }
}

function editBroker(brokerId) {

    const fetchBrokerInfo = (brokerId) => {
        fetch(`/api/kafka/brokers/${brokerId}`, {
            method: 'GET',
        })
            .then(response => response.json())
            .then(data => {
                setBrokerInfo(data);
                openModal('editBrokerModal');
            })
            .catch(error => {
                console.error('에러:', error);
            });
    }

    const setBrokerInfo = (brokerInfo) => {
        const modal = document.getElementById('editBrokerModal');
        modal.querySelector('#editBrokerId').value = brokerInfo.id;
        modal.querySelector('#editBrokerAlias').value = brokerInfo.alias;
        modal.querySelector('#oldAlias').value = brokerInfo.alias;
        modal.querySelector('#editBrokerHost').value = brokerInfo.bootstrapServer.host;
        modal.querySelector('#oldHost').value = brokerInfo.bootstrapServer.host;
        modal.querySelector('#editBrokerPort').value = brokerInfo.bootstrapServer.port;
        modal.querySelector('#oldPort').value = brokerInfo.bootstrapServer.port;
    }

    fetchBrokerInfo(brokerId);
}

function updateBroker() {

    const getUserInput = () => {
        const editModal = document.getElementById('editBrokerModal');
        const id = editModal.querySelector('#editBrokerId').value;
        const alias = editModal.querySelector('#editBrokerAlias').value;
        const oldAlias = editModal.querySelector('#oldAlias').value;
        const host = editModal.querySelector('#editBrokerHost').value;
        const oldHost = editModal.querySelector('#oldHost').value;
        const port = editModal.querySelector('#editBrokerPort').value;
        const oldPort = editModal.querySelector('#oldPort').value;

        return {
            id: id,
            alias: alias,
            host: host,
            port: port,
            oldAlias: oldAlias,
            oldHost: oldHost,
            oldPort: oldPort,
        };
    }

    const validUserInput = (userInputData) => {
        console.log(userInputData);
        if (userInputData.id === '') {
            return false;
        }

        if (userInputData.alias === '') {
            return false;
        }

        if (userInputData.oldAlias === '') {
            return false;
        }

        if (userInputData.host === '') {
            return false;
        }

        if (userInputData.oldHost === '') {
            return false;
        }

        if (userInputData.port === '') {
            return false;
        }

        if (userInputData.oldPort === '') {
            return false;
        }

        return true;
    }

    const fetchBrokerUpdate = (brokerInfo) => {
        fetch(`/api/kafka/brokers/${brokerInfo.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(brokerInfo),
        })
            .then(response => response.json())
            .then(data => {
                if (data.id != null) {
                    loadBrokers();
                    closeModal('editBrokerModal');
                    clearModalInputs('editBrokerModal');
                }

            })
            .catch(error => {
                console.error('에러:', error);
            });
    }

    const updateBrokerData = getUserInput();
    if (validUserInput(updateBrokerData)) {
        fetchBrokerUpdate(updateBrokerData);
    } else {
        Alert.error("모든 항목을 입력해주시기 바랍니다.");
    }
}

function deleteBroker(brokerAlias, brokerId) {

    const fetchDeleteBroker = (brokerId) => {
        fetch(`/api/kafka/brokers/${brokerId}`, {
            method: 'DELETE',
        })
            .then(response => response.json())
            .then(data => {
                if (data) {
                    loadBrokers();
                }
            })
            .catch(error => {
                console.error('에러:', error);
            });

    }

    Confirm.confirmDangerWithCallback(`${brokerAlias} 삭제하시겠습니까?`, () => fetchDeleteBroker(brokerId));
}