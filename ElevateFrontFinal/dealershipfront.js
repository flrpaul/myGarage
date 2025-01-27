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

// Anazhthsh autokinhtwn
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


//----------------------- Pros8hkh Autokinhtou ---------------------//
 

document.getElementById("add-car-form").addEventListener("submit", async (event) => {
    event.preventDefault();
  
    const BASE_URL = "http://localhost:8080";

    const brand = document.getElementById("add-car-brand").value.trim();
    const model = document.getElementById("add-car-model").value.trim();
    const fuelType = document.getElementById("add-car-fuel").value.trim();
    const engine = document.getElementById("add-car-engine").value.trim();
    const availability = document.getElementById("add-car-availability").value.trim();
    const seats = document.getElementById("add-car-seats").value.trim();
    const price = parseFloat(document.getElementById("add-car-price").value);
    const info = document.getElementById("add-car-info").value;
    const did = document.getElementById("add-car-did").value;
  
    try {
      const response = await fetch(`${BASE_URL}/addcar`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        // id, dealerShip:{id:did}
        body: JSON.stringify({ brand, model, fuelType, engine, price, availability, seats, info, dealerShip:{afm:did} }),
      });
  
      if (response.ok) {
        document.getElementById("add-car-message").textContent = "Car added successfully!";
        document.getElementById("add-car-message").style.color = "green";
  
        
        loadCars();
      } else {
        document.getElementById("add-car-message").textContent = "Failed to add car.";
        document.getElementById("add-car-message").style.color = "red";
      }
    } catch (error) {
      document.getElementById("add-car-message").textContent = "An error occurred!";
      document.getElementById("add-car-message").style.color = "red";
    }
  });

  //------------------- Ananewsh autokinhtwn --------------------------//

  document.getElementById("update-car-form").addEventListener("submit", async (event) => {
    event.preventDefault();

    const baseUrl = "http://localhost:8080";
    const carId = document.getElementById("update-car-id").value.trim();
    const newAvailability = parseInt(document.getElementById("update-car-availability").value, 10);

    if (!carId || isNaN(newAvailability)) {
        document.getElementById("update-car-message").textContent = "Please provide valid inputs.";
        document.getElementById("update-car-message").style.color = "red";
        return;
    }

    try {
        const response = await fetch(`${baseUrl}/updatecaravailability?carId=${carId}&newavailability=${newAvailability}`, {
            method: "PUT",
        });

        if (response.ok) {
            const result = await response.text();
            document.getElementById("update-car-message").textContent = `Success ${result}`;
            document.getElementById("update-car-message").style.color = "green";
        } else {
            const error = await response.text();
            document.getElementById("update-car-message").textContent = `Houston we have a problem, ${carId} is not in the car list`;
            document.getElementById("update-car-message").style.color = "red";
        }
    } catch (error) {
        document.getElementById("update-car-message").textContent = "An error occurred while updating availability.";
        document.getElementById("update-car-message").style.color = "red";
    }
});

// --------------------------- Log Out Button ------------------------------- //
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