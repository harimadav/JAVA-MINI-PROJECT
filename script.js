// ✅ Temporary local data (will be replaced by backend data)
let books = [
  { id: 1, title: "Java Programming", author: "Herbert Schildt", available: true },
  { id: 2, title: "C Programming", author: "Dennis Ritchie", available: true },
  { id: 3, title: "Database Systems", author: "Elmasri & Navathe", available: true },
  { id: 4, title: "Data Structures", author: "Narasimha Karumanchi", available: true }
];

let borrowedBooks = [];
let renewedBooks = [];
let returnedBooks = [];

// 🟩 Load available books (dynamic from backend later)
function loadBooks() {
  const tableBody = document.querySelector("#bookTable tbody");
  tableBody.innerHTML = "";
  books.forEach(book => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${book.id}</td>
      <td>${book.title}</td>
      <td>${book.author}</td>
      <td class="${book.available ? "available" : "borrowed"}">
        ${book.available ? "Available" : "Borrowed"}
      </td>
    `;
    tableBody.appendChild(row);
  });
}

// 🟦 Borrow
function borrowBook() {
  const id = parseInt(document.getElementById("bookId").value);
  const name = document.getElementById("userName").value;
  if (!id || !name) return alert("Please enter all fields");

  const book = books.find(b => b.id === id);
  if (!book) return alert("Invalid Book ID");
  if (!book.available) return alert("Book already borrowed");

  book.available = false;
  borrowedBooks.push({
    id: book.id,
    title: book.title,
    user: name,
    borrowDate: new Date().toLocaleDateString(),
    returnDate: "-"
  });
  loadBooks();
  alert(`${book.title} borrowed successfully by ${name}`);
}

// 🟥 Return
function returnBook() {
  const id = parseInt(document.getElementById("bookId").value);
  const record = borrowedBooks.find(b => b.id === id);
  if (!record) return alert("Book not found in borrowed list");

  record.returnDate = new Date().toLocaleDateString();
  returnedBooks.push({ ...record });
  books.find(b => b.id === id).available = true;
  borrowedBooks = borrowedBooks.filter(b => b.id !== id);
  loadBooks();
  showReturned();
  alert("Book returned successfully!");
}

// 🟨 Renew
function renewBook() {
  const id = parseInt(document.getElementById("bookId").value);
  const record = borrowedBooks.find(b => b.id === id);
  if (!record) return alert("Book is not borrowed!");

  record.returnDate = "Renewed - " + new Date().toLocaleDateString();
  renewedBooks.push({ ...record, renewDate: new Date().toLocaleDateString() });
  alert("Book renewed successfully!");
}

// 🧾 View Borrowed
function showBorrowed() {
  toggleSection("borrowedListSection");
  const tableBody = document.querySelector("#borrowedTable tbody");
  tableBody.innerHTML = "";
  borrowedBooks.forEach(b => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${b.id}</td>
      <td>${b.title}</td>
      <td>${b.user}</td>
      <td>${b.borrowDate}</td>
      <td>${b.returnDate}</td>
    `;
    tableBody.appendChild(row);
  });
}

// 🔁 View Renewed
function showRenewed() {
  toggleSection("renewedListSection");
  const tableBody = document.querySelector("#renewedTable tbody");
  tableBody.innerHTML = "";
  renewedBooks.forEach(b => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${b.id}</td>
      <td>${b.title}</td>
      <td>${b.user}</td>
      <td>${b.renewDate}</td>
    `;
    tableBody.appendChild(row);
  });
}

// 🔙 View Returned
function showReturned() {
  toggleSection("returnedListSection");
  const tableBody = document.querySelector("#returnedTable tbody");
  tableBody.innerHTML = "";
  returnedBooks.forEach(b => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${b.id}</td>
      <td>${b.title}</td>
      <td>${b.user}</td>
      <td>${b.returnDate}</td>
    `;
    tableBody.appendChild(row);
  });
}

// 📋 Toggle Sections (only one visible at a time)
function toggleSection(sectionId) {
  document.querySelectorAll("main section").forEach(sec => {
    if (sec.id.includes("ListSection"))
      sec.style.display = "none";
  });
  document.getElementById(sectionId).style.display = "block";
}

window.onload = loadBooks;
