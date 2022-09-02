window.setInterval(getAfterLoad, 5000);

function getAfterLoad() {
    getData('http://localhost:8080/api/urls');
}

function refreshPage() {
    setTimeout(window.location.reload(), 1000)
}

function newTab(link) {
    window.open(link, "_blank");
}

function getData(url) {
fetch(url)
  .then((response) => response.json())
  .then((data) => {
        var tbody = document.getElementById("tbody");
        tbody.innerHTML = '';
        var urlsDiv = document.getElementById("urls");
        if (data.length > 0) {
            urlsDiv.style.display = "block";
        }
        data = data.slice(0, 100);
        for (const element of data) {
            var tr = document.createElement("tr");

            var tdShortenUrl = document.createElement("td");
            var linkShortenUrl = document.createElement("a");
            linkShortenUrl.innerHTML =
            `<a target="_blank" href=${element["shortenUrl"]}>${element["shortenUrl"]}</a>`;
            tdShortenUrl.appendChild(linkShortenUrl);
            tr.appendChild(tdShortenUrl);

            var tdCount = document.createElement("td");
            var paragraphCount = document.createElement("p");
            paragraphCount.innerHTML = "<p> " + element["count"] +  "</p>";
            tdCount.appendChild(paragraphCount);
            tr.appendChild(tdCount);

            var tdDetailsUrl = document.createElement("td");
            var linkDetailsUrl = document.createElement("a");
            linkDetailsUrl.innerHTML =
            `<a target="_blank" href=${element["shortenUrl"]}/details>See Details</a>`;
            tdDetailsUrl.appendChild(linkDetailsUrl);
            tr.appendChild(tdDetailsUrl);

            tbody.appendChild(tr);
        }
  })
}
