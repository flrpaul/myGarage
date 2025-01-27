document.addEventListener("DOMContentLoaded", function () {
    const registerRole = document.getElementById("registerRole");
    const registerEmailField = document.getElementById("registerEmailField");
    const registerAfmField = document.getElementById("registerAfmField");
    const registerPasswordField = document.getElementById("registerPasswordField");
    const registerFnameField = document.getElementById("registerFnameField");
    const registerLnameField = document.getElementById("registerLnameField");
    const registerCompanyNameField = document.getElementById("registerCompanyNameField");
    const registerOwnerField = document.getElementById("registerOwnerField");
    const registerSubmitButton = document.getElementById("registerSubmitButton");
    const message = document.getElementById("message");

    registerRole.addEventListener("change", function () {
        const selectedRole = registerRole.value;

        if (selectedRole === "citizen") {
            registerFnameField.classList.remove("hidden");
            registerLnameField.classList.remove("hidden");
            registerEmailField.classList.remove("hidden");
            registerPasswordField.classList.remove("hidden");
            registerSubmitButton.classList.remove("hidden");

            registerCompanyNameField.classList.add("hidden");
            registerOwnerField.classList.add("hidden");
            registerAfmField.classList.remove("hidden");
        } else if (selectedRole === "dealership") {
            registerCompanyNameField.classList.remove("hidden");
            registerOwnerField.classList.remove("hidden");
            registerAfmField.classList.remove("hidden");
            registerPasswordField.classList.remove("hidden");
            registerSubmitButton.classList.remove("hidden");

            registerFnameField.classList.add("hidden");
            registerLnameField.classList.add("hidden");
            registerEmailField.classList.add("hidden");
        } else {
            registerEmailField.classList.add("hidden");
            registerAfmField.classList.add("hidden");
            registerPasswordField.classList.add("hidden");
            registerFnameField.classList.add("hidden");
            registerLnameField.classList.add("hidden");
            registerCompanyNameField.classList.add("hidden");
            registerOwnerField.classList.add("hidden");
            registerSubmitButton.classList.add("hidden");
        }
    });

    const form = document.querySelector("form");
    form.addEventListener("submit", async function (event) {
        event.preventDefault();

        const role = registerRole.value;
        let data;

        if (role === "citizen") {
            data = {
                role: role,
                email: registerEmailField.querySelector("input").value,
                fname: registerFnameField.querySelector("input").value,
                lname: registerLnameField.querySelector("input").value,
                password: registerPasswordField.querySelector("input").value,
                afm : registerAfmField.querySelector("input").value,
            };

            if (!data.email || !data.fname || !data.lname || !data.password) {
                message.textContent = "Please fill in all the fields for Citizen.";
                message.style.color = "red";
                return;
            }
        } else if (role === "dealership") {
            data = {
                role: role,
                afm: registerAfmField.querySelector("input").value,
                companyname: registerCompanyNameField.querySelector("input").value,
                owner: registerOwnerField.querySelector("input").value,
                password: registerPasswordField.querySelector("input").value,
            };

            if (!data.afm || !data.companyname || !data.owner || !data.password) {
                message.textContent = "Please fill in all the fields for Dealership.";
                message.style.color = "red";
                return;
            }
        } else {
            message.textContent = "Please select a role before submitting.";
            message.style.color = "red";
            return;
        }

        console.log("Data to be sent:", data);

        try {
            const response = await fetch("http://localhost:8080/register", {
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
            } 
            else {
                const error = await response.text();
                message.textContent = error;
                message.style.color = "red";
            }

                if(role==="citizen" && response.ok){
                    window.location.href ="citizenfront.html";
                } else if (role ==="dealership" && response.ok) {
                window.location.href = "dealershipfront.html"
                } else {
                    message.textContent = "First Name is already taken";
                }
        } catch (err) {
            message.textContent = `Action failed: ${err.message}`;
            message.style.color = "red";
        }
    });
});
