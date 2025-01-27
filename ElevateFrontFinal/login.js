document.addEventListener("DOMContentLoaded", function () {
    
    const loginRole = document.getElementById("loginRole");
    const loginEmailField = document.getElementById("loginEmailField");
    const loginAfmField = document.getElementById("loginAfmField");
    const loginPasswordField = document.getElementById("loginPasswordField");
    const loginSubmitButton = document.getElementById("loginSubmitButton");
    const message = document.getElementById("message");

    loginRole.addEventListener("change", function () {
        const selectedRole = loginRole.value;

        
        if (selectedRole === "citizen") {
            loginEmailField.classList.remove("hidden");
            loginAfmField.classList.add("hidden");
            loginPasswordField.classList.remove("hidden");
            loginSubmitButton.classList.remove("hidden");
        } else if (selectedRole === "dealership") {
            loginAfmField.classList.remove("hidden");
            loginEmailField.classList.add("hidden");
            loginPasswordField.classList.remove("hidden");
            loginSubmitButton.classList.remove("hidden");
        } else {
            loginEmailField.classList.add("hidden");
            loginAfmField.classList.add("hidden");
            loginPasswordField.classList.add("hidden");
            loginSubmitButton.classList.add("hidden");
        }
    });

    
    const form = document.querySelector("form");
    form.addEventListener("submit", async function (event) {
        event.preventDefault();

        const role = loginRole.value;
        let data;
        const password = loginPassword.value;
        

        if (role === "citizen") {
            const email = document.getElementById("loginEmail").value;
            data = {
                role: role,
                email: email,
                password: password,
            }
        } else if (role === "dealership") {
            const afm = document.getElementById("loginAfm").value;
            data = {
                role: role,
                afm: afm,
                password: password,
            }
        } else {
            message.textContent = "Please select a valid role for this.";
            message.style.color = "red";
        }


        try {
            const response = await fetch("http://localhost:8080/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            });

            if (response.ok) {
                const result = await response.text();
                message.textContent = result;
                message.style.color = "green";

                if(role==="citizen"){
                    window.location.href ="citizenfront.html";
                } else if (role ==="dealership") {
                    window.location.href = "dealershipfront.html"
                }

            } else {
                const error = await response.text();
                message.textContent = error;
                message.style.color = "red";
            }
        } catch (err) {
            message.textContent = `Action failed: ${err.message}`;
            message.style.color = "red";
        }
    });
});
