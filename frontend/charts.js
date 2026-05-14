async function loadCharts(){

    const res = await fetch("http://localhost:8080/product/revenue-details");
    const data = await res.json();

    // DAILY CHART
    const ctx = document.getElementById("dailyChart").getContext("2d");

    new Chart(ctx, {
        type: "bar",
        data: {
            labels: data.dailyLabels,
            datasets: [{
                label: "Daily Revenue",
                data: data.dailyValues,
                backgroundColor: "gold"
            }]
        }
    });

    // MONTHLY CHART
    const ctx2 = document.getElementById("monthlyChart").getContext("2d");

    new Chart(ctx2, {
        type: "line",
        data: {
            labels: data.monthlyLabels,
            datasets: [{
                label: "Monthly Revenue",
                data: data.monthlyValues,
                borderColor: "gold",
                fill: false
            }]
        }
    });

}

window.onload = loadCharts;