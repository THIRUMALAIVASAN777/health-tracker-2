
async function calculateMacros() {
    const age = document.getElementById("age").value;
    const weight = document.getElementById("weight").value;
    const height = document.getElementById("height").value;
    const gender = document.getElementById("gender").value;
    const activityLevel = document.getElementById("activityLevel").value;

    if (!age || !weight || !height) {
        alert("Please fill all fields");
        return;
    }

    const url =
        `http://localhost:8080/api/macros?age=${age}&weight=${weight}&height=${height}&gender=${gender}&activityLevel=${activityLevel}`;

    try {
        const response = await fetch(url);

        if (!response.ok) {
            throw new Error("Failed to calculate macros");
        }

        const data = await response.json();

        document.getElementById("calories").textContent = data.calories;
        document.getElementById("protein").textContent = data.protein;
        document.getElementById("carbs").textContent = data.carbs;
        document.getElementById("fat").textContent = data.fat;

    } catch (error) {
        console.error(error);
        alert("Unable to connect to backend. Make sure Spring Boot is running.");
    }
}


function calculateBMI() {
    const weight = parseFloat(document.getElementById("bmiWeight").value);
    const height = parseFloat(document.getElementById("bmiHeight").value);

    const bmiValue = document.getElementById("bmiValue");
    const bmiCategory = document.getElementById("bmiCategory");

    if (isNaN(weight) || isNaN(height) || weight <= 0 || height <= 0) {
        bmiValue.textContent = "--";
        bmiCategory.textContent = "Please enter valid weight and height";
        bmiCategory.className = "bmi-invalid";
        return;
    }

    const heightInMeters = height / 100;
    const bmi = weight / (heightInMeters * heightInMeters);

    bmiValue.textContent = bmi.toFixed(2);

    if (bmi < 18.5) {
        bmiCategory.textContent = "Underweight";
        bmiCategory.className = "bmi-underweight";
    } else if (bmi < 25) {
        bmiCategory.textContent = "Normal Weight";
        bmiCategory.className = "bmi-normal";
    } else if (bmi < 30) {
        bmiCategory.textContent = "Overweight";
        bmiCategory.className = "bmi-overweight";
    } else {
        bmiCategory.textContent = "Obese";
        bmiCategory.className = "bmi-obese";
    }
}


async function saveHealthRecord() {
    const email = document.getElementById("email").value.trim();

    const steps = parseInt(document.getElementById("steps").value);
    const weight = parseFloat(document.getElementById("weight").value);
    const calories = parseInt(document.getElementById("calories").value);
    const water = parseFloat(document.getElementById("water").value);
    const sleepHours = parseFloat(document.getElementById("sleepHours").value);
    const heartRate = parseInt(document.getElementById("heartRate").value);

    if (!email) {
        alert("Please enter your email");
        return;
    }

    if (
        isNaN(steps) ||
        isNaN(weight) ||
        isNaN(calories) ||
        isNaN(water) ||
        isNaN(sleepHours) ||
        isNaN(heartRate)
    ) {
        alert("Please fill all health record fields");
        return;
    }

    const healthRecord = {
        steps,
        weight,
        calories,
        water,
        sleepHours,
        heartRate
    };

    try {
        const response = await fetch(
            "http://localhost:8080/api/health?email=" +
            encodeURIComponent(email),
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(healthRecord)
            }
        );

        if (!response.ok) {
            throw new Error("Failed to save health record");
        }

        const data = await response.json();

        document.getElementById("recordMessage").textContent =
            "Health record saved successfully!";

        console.log("Saved record:", data);

    } catch (error) {
        console.error(error);

        document.getElementById("recordMessage").textContent =
            "Unable to save health record. Make sure Spring Boot is running.";
    }
}
