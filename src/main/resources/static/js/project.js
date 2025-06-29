window.submitForm = function (event, index) {
    event.preventDefault();
    const form = event.target;
    const button = form.querySelector('button');
    const link = button.getAttribute('data-link');
    const resultDiv = document.getElementById('result-' + index);

    const inputs = form.querySelectorAll('input');
    const params = Array.from(inputs).map(input =>
        encodeURIComponent(input.name) + '=' + encodeURIComponent(input.value)
    ).join('&');

    const url = `${link}?${params}`;

    resultDiv.innerText = "Loading...";

    fetch(url)
        .then(res => res.text())
        .then(raw => {
            try {
                const json = JSON.parse(raw);

                if (Array.isArray(json) && json.length && typeof json[0] === 'object') {
                    showJsonView(json);
                    resultDiv.innerText = '';
                } else {
                    resultDiv.innerText = raw;
                }
            } catch (e) {
                resultDiv.innerText = raw;
            }
        })
        .catch(err => {
            resultDiv.innerText = 'Error: ' + err.message;
        });
};

function showJsonView(jsonArray) {
    const headers = Object.keys(jsonArray[0]);
    const headerRow = headers.map(key => `<th class="border-b p-2">${key}</th>`).join('');
    document.getElementById('json-headers').innerHTML = `<tr>${headerRow}</tr>`;

    const rows = jsonArray.map(obj =>
        `<tr>${headers.map(h => `<td class="p-2 border-b">${obj[h]}</td>`).join('')}</tr>`
    ).join('');
    document.getElementById('json-body').innerHTML = rows;

    const viewer = document.getElementById('json-viewer');
    viewer.classList.remove('hidden');
    viewer.scrollTop = 0;
}

function closeJsonView() {
    document.getElementById('json-viewer').classList.add('hidden');
    window.scrollTo({ top: 0, behavior: 'smooth' }); // Optional: scroll to top
}

