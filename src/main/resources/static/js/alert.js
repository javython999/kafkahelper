function sweetAlertIfNull(value, defaultValue) {
    return value ?? defaultValue;
}
window.Alert = {
    info: function(message) {
        Swal.fire({
            title: 'Info',
            text: sweetAlertIfNull(message, 'Info'),
            icon: 'info',
            confirmButtonText: '확인',
            customClass: {
                container: 'custom-swal-zindex'
            }
        });
    },

    error: function(message) {
        Swal.fire({
            title: 'Error',
            text: sweetAlertIfNull(message, 'Error'),
            icon: 'error',
            confirmButtonText: '확인',
            customClass: {
                container: 'custom-swal-zindex'
            }
        });
    },

    success: function(message) {
        Swal.fire({
            title: sweetAlertIfNull(message, 'Success'),
            icon: "success",
            confirmButtonText: '확인',
            draggable: false,
            customClass: {
                container: 'custom-swal-zindex'
            }
        });
    },

    successWithCallback: function(message, callbackFunction) {
        Swal.fire({
            title: sweetAlertIfNull(message, 'Success'),
            icon: "success",
            confirmButtonText: '확인',
            draggable: false,
            customClass: {
                container: 'custom-swal-zindex'
            }
        }).then((result) => {
            if (result.isConfirmed && typeof callbackFunction === 'function') {
                callbackFunction();
            }
        });
    },
};

window.Confirm = {
    confirmWithCallback: function(message, callbackFunction) {
        Swal.fire({
            title:  sweetAlertIfNull(message, 'Confirm'),
            showCancelButton: true,
            confirmButtonText: "Ok",
        }).then((result) => {
            if (result.isConfirmed) {
                callbackFunction();
            }
        });
    },

    confirmDangerWithCallback: function(message, callbackFunction) {
        Swal.fire({
            title:  sweetAlertIfNull(message, 'Confirm'),
            showConfirmButton: false,
            showCancelButton: true,
            showDenyButton: true,
            denyButtonText: `delete`,
        }).then((result) => {
            if (result.isDenied) {
                callbackFunction();
            }
        });
    }
}

window.Toast = {
    info: function(message) {
        this.toast('info', sweetAlertIfNull(message, 'Info'));
    },

    warning: function(message) {
        this.toast('warning', sweetAlertIfNull(message, 'Warning'));
    },

    question: function(message) {
        this.toast('question', sweetAlertIfNull(message, 'Question'));
    },

    error: function(message) {
        this.toast('error', sweetAlertIfNull(message, 'Error'));
    },

    success: function(message) {
        this.toast('success', sweetAlertIfNull(message, 'Success'));
    },

    toast: function(icon, message) {
        const Toast = Swal.mixin({
            toast: true,
            position: "top-end",
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
            didOpen: (toast) => {
                toast.onmouseenter = Swal.stopTimer;
                toast.onmouseleave = Swal.resumeTimer;
            }
        });
        Toast.fire({
            icon: icon,
            title: message
        });
    }
}
