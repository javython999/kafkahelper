document.addEventListener("DOMContentLoaded", () => {
    initModalCloseAction();
    initSidebar();

    window.addEventListener("resize", function () {
        const sidebar = document.querySelector(".pc-sidebar");
        if (window.innerWidth > 1024) {
            // 데스크톱 모드: 항상 보이게
            sidebar.style.left = "0px";
        } else {
            // 모바일 모드: 닫힌 상태로 시작
            sidebar.style.left = "-280px";
        }
    });
});

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

function initSidebar() {
    const mobileCollapse = document.getElementById("mobile-collapse");
    const sidebar = document.querySelector(".pc-sidebar");

    // 햄버거 버튼 클릭 → 열기
    mobileCollapse.addEventListener("click", function (e) {
        e.preventDefault();
        sidebar.style.left = "0px";
    });

    // 외부 클릭 → 닫기 (모바일 전용)
    document.addEventListener("click", function (event) {
        const isMobile = window.innerWidth <= 1024; // 모바일 기준
        if (!isMobile) return; // 데스크탑이면 무시

        if (sidebar.style.left === "0px") {
            if (!sidebar.contains(event.target) && !mobileCollapse.contains(event.target)) {
                sidebar.style.left = "-280px";
            }
        }
    });
}