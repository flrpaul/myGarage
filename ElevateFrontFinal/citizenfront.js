document.addEventListener("DOMContentLoaded", () => {
    const baseUrl = "http://localhost:8080";

// Emfanish autokinhtwn apo thn vash

const loadCars = async () => {
    try {
        const response = await fetch(`${baseUrl}/cars`);
        if(response.ok) {
            const cars = await response.json();
            const carsTableBody = document.querySelector("#carsTable tbody");
            carsTableBody.innerHTML = "";

            cars.forEach((car) => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${car.brand}</td>
                    <td>${car.model}</td>
                    <td>${car.fuelType}</td>
                    <td>${car.engine}</td>
                    <td>${car.price}</td>
                    <td>${car.availability}</td>
                    <td>${car.info}</td>
                    <td>${car.seats}</td>
                `;
                carsTableBody.appendChild(row);
            });
       } else {
        console.error("Failed to fetch cars.");
       }
    } catch (error) {
        console.error("Error loading cars", error);
    }
};
loadCars();

//---------------------- Anazhthsh autokinhtwn ---------------------------------//
const searchForm = document.getElementById("searchForm");
searchForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    const brand = document.getElementById("brand").value.trim();
    const model = document.getElementById("model").value.trim();
    const fuelType = document.getElementById("fuelType").value.trim();
    const engine = document.getElementById("engine").value.trim();

    try {
        
        const queryParams = new URLSearchParams();
        if (brand) queryParams.append("brand", brand);
        if (model) queryParams.append("model", model);
        if (fuelType) queryParams.append("fuelType", fuelType);
        if (engine) queryParams.append("engine", engine);

        
        if (!queryParams.toString()) {
            document.getElementById("searchResults").textContent = "Παρακαλώ εισάγετε τουλάχιστον ένα πεδίο.";
            return;
        }

        const response = await fetch(`${baseUrl}/searchcars?${queryParams.toString()}`);

        if (response.ok) {
            const cars = await response.json();
            const resultsContainer = document.getElementById("searchResults");

            if (cars.length > 0) {
                resultsContainer.innerHTML = cars.map(
                    (car) =>
                        `<p>${car.brand} ${car.model} - ${car.fuelType} - Engine: ${car.engine}</p>`
                ).join("");
            } else {
                resultsContainer.textContent = "Βάση της αναζήτης σας, δεν βρέθηκε κάτι στην βάση δεδομένων.Δοκιμάστε ξανά!";
            }
        } else {
            document.getElementById("searchResults").textContent = "No cars found.";
        }
    } catch (error) {
        document.getElementById("searchResults").textContent = "Error fetching cars.";
    }
});

//---------------  Agora Autokinhtou  ---------------------//

document.getElementById("purchase-car-form").addEventListener("submit", async (event) => {
    event.preventDefault();
    const BASE_URL = "http://localhost:8080";
    const carModel = document.getElementById("carModel").value.trim(); // carId = carModel, na mhn mperdeutw

    try {
        
        const response = await fetch(`${BASE_URL}/purchasecar?carId=${carModel}`, {
            method: "POST", 
        });

        if (response.ok) {
            const result = await response.text();
            document.getElementById("purchase-message").textContent = `You have successfully purchased the car.`;
            document.getElementById("purchase-message").style.color = "green";
        } else {
            const error = await response.text();
            document.getElementById("purchase-message").textContent = `Car not found! Check the available ones!`;
            document.getElementById("purchase-message").style.color = "red";
        }
    } catch (error) {
        document.getElementById("purchase-message").textContent = "An error occurred while purchasing the car.";
        document.getElementById("purchase-message").style.color = "red";
    }
});

// ----------------- Test drive booking  ----------------------------//

document.getElementById("book-test-drive-form").addEventListener("submit", async (event) => {
    event.preventDefault();
    
    const BASE_URL = "http://localhost:8080";
    const carModel = document.getElementById("test-drive-car-model").value.trim();
    const userName = document.getElementById("test-drive-user-name").value.trim();
    const testDriveTime = document.getElementById("test-drive-time").value;
    const testDriveDate = document.getElementById("test-drive-date").value;
    // const id = document.getElementById("test-drive-id").value;
  
    const booking = {
        car: { model: carModel },
        citizen: {fname: userName},
        date: testDriveDate,
        time: testDriveTime,
        // id: id,
    };
  
    try {
        
        const response = await fetch(`${BASE_URL}/booktestdrive`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(booking),
        });
  
        if (response.ok) {
            const result = await response.text();
            document.getElementById("test-drive-message").textContent = `Success: ${result}`;
            document.getElementById("test-drive-message").style.color = "green";
        } else {
            const error = await response.text();
            document.getElementById("test-drive-message").textContent = `Houston we have a problem, check your inputs`;
            document.getElementById("test-drive-message").style.color = "red";
        }
    } catch (error) {
        document.getElementById("test-drive-message").textContent = "An error occurred while booking the test drive.";
        document.getElementById("test-drive-message").style.color = "red";
    }
  });

            // ----------------- Log Out Button ---------------------------------- //

            const logoutButton = document.getElementById('logoutButton');

            logoutButton.addEventListener("click", () => {
                const confirmLogout = confirm(`Are you sure?`);

                if(confirmLogout) {
                    localStorage.removeItem('userToken');
                    sessionStorage.clear();
                }
                alert('You have successfully logged out!');
                window.location.href = 'login.html';
            })
});