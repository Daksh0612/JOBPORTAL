document.addEventListener("DOMContentLoaded", function () {
    const roleSelect = document.querySelector(".role-select");
    const recruiterOnlyField = document.querySelector(".recruiter-only");

    if (roleSelect && recruiterOnlyField) {
        const toggleCompanyField = function () {
            const isRecruiter = roleSelect.value === "ROLE_RECRUITER";
            recruiterOnlyField.classList.toggle("d-none", !isRecruiter);
        };

        roleSelect.addEventListener("change", toggleCompanyField);
        toggleCompanyField();
    }
});
