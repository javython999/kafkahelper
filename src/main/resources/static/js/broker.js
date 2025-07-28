document.addEventListener("DOMContentLoaded", () => {
    loadBrokers();
});

document.querySelectorAll('.modal').forEach(modal => {
   modal.addEventListener('hidden.bs.modal', () => {
        clearModalInputs(modal.getAttribute('id'));
    });
});

document.getElementById('editBrokerModal').addEventListener('hidden.bs.modal', () => {
    clearModalInputs('editBrokerModal');
});

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
                <td>${broker.host}</td>
                <td>${broker.port}</td>
                <td>
                    <button type="button" 
                            onclick="editBroker(${broker.id})"
                            class="btn btn-icon btn-outline-primary btn-sm">
                        <svg xmlns="http://www.w3.org/2000/svg" 
                             width="24" 
                             height="24" 
                             fill="none"
                             viewBox="0 0 24 24" 
                             stroke="currentColor" 
                             stroke-width="2" 
                             stroke-linecap="round" 
                             stroke-linejoin="round" 
                             class="feather feather-edit">
                             <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
                             <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
                        </svg>
                        <span>edit</span>
                    </button>
                    <button type="button" 
                            onclick="deleteBroker(${broker.id})"
                            class="btn  btn-outline-danger btn-sm">
                        <svg xmlns="http://www.w3.org/2000/svg" 
                             width="24" 
                             height="24" 
                             viewBox="0 0 24 24" 
                             fill="none" 
                             stroke="currentColor" 
                             stroke-width="2" 
                             stroke-linecap="round" 
                             stroke-linejoin="round" 
                             class="feather feather-trash">
                             <polyline points="3 6 5 6 21 6"></polyline>
                             <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
                         </svg>
                        <span>delete</span>
                    </button>
                </td>
            </tr>
        `;
    }

    fetchBrokers();
}

function openModal(modalId) {
    const modal = new bootstrap.Modal(document.getElementById(modalId));
    modal.show();
}

function closeModal() {
    document.querySelectorAll('.btn-close')
        .forEach((button) => button.click());
}

function clearModalInputs(modalId) {
    const modal = document.getElementById(modalId);
    modal.querySelectorAll('input').forEach(input => input.value = '');
}

function saveBroker() {

    const getUserInput = () => {
        const registerModal = document.getElementById('registerBrokerModal');
        const alias = registerModal.querySelector('#registerBrokerAlias').value;
        const host = registerModal.querySelector('#registerBrokerHost').value;
        const port = registerModal.querySelector('#registerBrokerPort').value;

        return {
            alias: alias,
            host: host,
            port: port,
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
        modal.querySelector('#editBrokerHost').value = brokerInfo.host;
        modal.querySelector('#oldHost').value = brokerInfo.host;
        modal.querySelector('#editBrokerPort').value = brokerInfo.port;
        modal.querySelector('#oldPort').value = brokerInfo.port;
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

function deleteBroker(brokerId) {

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

    Confirm.confirmDangerWithCallback("삭제하시겠습니까?", () => fetchDeleteBroker(brokerId));
}