function initModalCloseAction() {
    document.querySelectorAll('.modal').forEach(modal => {
        modal.addEventListener('hidden.bs.modal', () => {
            clearModalInputs(modal.getAttribute('id'));
        });
    });
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