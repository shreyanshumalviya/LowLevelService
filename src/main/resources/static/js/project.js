function submitForm(event, index) {
        console.log("submitForm called with index:", index);
        event.preventDefault();
        const form = event.target;
        const link = form.querySelector('button').getAttribute('data-link');
        const resultDiv = document.getElementById('result-' + index);

        const inputs = form.querySelectorAll('input');
        const params = Array.from(inputs).map(input =>
            encodeURIComponent(input.name) + '=' + encodeURIComponent(input.value)
        ).join('&');

        const url = `${link}?${params}`;

        resultDiv.innerText = "Loading...";

        fetch(url)
            .then(res => res.text())
            .then(text => resultDiv.innerText = text)
            .catch(err => resultDiv.innerText = 'Error: ' + err.message);
    }