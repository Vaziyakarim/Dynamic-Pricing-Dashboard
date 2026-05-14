//-----------------------------------------------------------
// API WRAPPER
//-----------------------------------------------------------
async function api(url, method = "GET", body = null) {
    try {
        const res = await fetch(url, {
            method,
            headers: {
                "Content-Type": "application/json"
            },
            body: body ? JSON.stringify(body) : null
        });

        const text = await res.text();

        try {
            return JSON.parse(text);
        } catch {
            return text;
        }

    } catch (error) {
        return "ERROR: Server unreachable";
    }
}

//-----------------------------------------------------------
// POPUP
//-----------------------------------------------------------
function showPopup(title, message) {
    document.getElementById("popupTitle").innerText = title;
    document.getElementById("popupMessage").innerText = message;
    document.getElementById("popup").classList.add("active");
}

function closePopup() {
    document.getElementById("popup").classList.remove("active");
}

//-----------------------------------------------------------
// PRODUCTS
//-----------------------------------------------------------
let allProducts = [];

//-----------------------------------------------------------
// LOAD PRODUCTS
//-----------------------------------------------------------
async function loadProducts() {

    const res = await fetch("http://localhost:8080/product/all");
    allProducts = await res.json();

    displayProducts(allProducts);
    attachFilters();
}

//-----------------------------------------------------------
// DISPLAY PRODUCTS
//-----------------------------------------------------------
function displayProducts(products) {

    const table = document.getElementById("productTableBody");

    if(!table) return;

    table.innerHTML = "";

    products.forEach((p,index)=>{

        table.innerHTML += `
        <tr>

        <td>${index+1}</td>
        <td>${p.id}</td>
        <td>${p.name}</td>
        <td>${p.basePrice}</td>
        <td>${p.finalPrice.toFixed(2)}</td>
        <td>${p.stock}</td>
        <td>${p.demand}</td>

        <td>
        <button class="nice-btn" onclick="quickEdit(${p.id})">Edit</button>
        <button class="nice-btn" onclick="deleteProduct(${p.id})">Delete</button>
        </td>

        </tr>
        `;
    });
}

//-----------------------------------------------------------
// QUICK EDIT POPUP
//-----------------------------------------------------------
let currentEditId = null;

function quickEdit(id) {

    currentEditId = id;

    const p = allProducts.find(x => x.id === id);

    document.getElementById("editBasePrice").value = p.basePrice;
    document.getElementById("editStock").value = p.stock;
    document.getElementById("editDemand").value = p.demand;

    document.getElementById("editPopup").classList.add("active");
}

function closeEdit() {
    document.getElementById("editPopup").classList.remove("active");
}
function saveEdit() {

const p = allProducts.find(x => x.id === currentEditId);

const body = {
    name: p.name,
    basePrice: Number(document.getElementById("editBasePrice").value),
    stock: Number(document.getElementById("editStock").value),
    demand: Number(document.getElementById("editDemand").value)
};

api(`http://localhost:8080/product/update/${currentEditId}`, "PUT", body)
.then(()=>{
closeEdit();
showPopup("Updated","Product updated successfully!");
loadProducts();
});

}
//-----------------------------------------------------------
// DELETE PRODUCT
//-----------------------------------------------------------
let deleteId = null;

function deleteProduct(id) {

    deleteId = id;

    document.getElementById("deleteText").innerText =
        "Are you sure you want to delete Product ID: " + id + "?";

    document.getElementById("deletePopup").classList.add("active");
}

function closeDelete() {
    document.getElementById("deletePopup").classList.remove("active");
}

function confirmDelete() {

    api(`http://localhost:8080/product/delete/${deleteId}`, "DELETE")
        .then(() => {
            closeDelete();
            showPopup("Deleted", "Product deleted successfully!");
            loadProducts();
        });
}

//-----------------------------------------------------------
// SEARCH + FILTER
//-----------------------------------------------------------
function applyFilters() {

    let searchText = document.getElementById("searchInput").value.toLowerCase();
    let stockFilter = document.getElementById("filterStock").value;
    let demandFilter = document.getElementById("filterDemand").value;

    let filtered = allProducts;

    if(searchText !== ""){
        filtered = filtered.filter(p =>
            p.name.toLowerCase().includes(searchText));
    }

    if(stockFilter === "low"){
        filtered = filtered.filter(p => p.stock < 10);
    } 
    else if(stockFilter === "high"){
        filtered = filtered.filter(p => p.stock >= 10);
    }

    if(demandFilter === "low"){
        filtered = filtered.filter(p => p.demand < 10);
    } 
    else if(demandFilter === "high"){
        filtered = filtered.filter(p => p.demand >= 10);
    }

    displayProducts(filtered);
}

function attachFilters(){
    document.getElementById("searchInput").oninput = applyFilters;
    document.getElementById("filterStock").onchange = applyFilters;
    document.getElementById("filterDemand").onchange = applyFilters;
}

//-----------------------------------------------------------
// INIT
//-----------------------------------------------------------
window.onload = loadProducts;