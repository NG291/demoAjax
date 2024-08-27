function addNewSmartPhone() {
    // Lấy dữ liệu từ form HTML
    let producer = $('#producer').val();
    let model = $('#model').val();
    let price = $('#price').val();
    let newSmartphone = {
        producer: producer,
        model: model,
        price: price
    };

    // Gọi phương thức AJAX để thêm mới smartphone
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newSmartphone),
        url: "http://localhost:8080/api/smartphones",
        success: successHandler
    });

    // Chặn sự kiện mặc định của thẻ
    event.preventDefault();
}

function successHandler() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/smartphones",
        success: function (data) {
            let content = '<table id="display-list" border="1"><tr>\n' +
                '<th>Producer</th>\n' +
                '<th>Model</th>\n' +
                '<th>Price</th>\n' +
                '<th>Delete</th>\n' +
                '<th>Update</th>\n' +
                '</tr>';
            for (let i = 0; i < data.length; i++) {
                content += getSmartphone(data[i]);
            }
            content += "</table>";
            $('#smartphoneList').html(content).show();
            $('#add-smartphone').hide();
            $('#display-create').show();
            $('#title').show();
        }
    });
}

function displayFormCreate() {
    $('#smartphoneList').hide();
    $('#add-smartphone').show();
    $('#display-create').hide();
    $('#title').hide();
}

function getSmartphone(smartphone) {
    return `<tr><td>${smartphone.producer}</td><td>${smartphone.model}</td><td>${smartphone.price}</td>` +
        `<td class="btn"><button class="deleteSmartphone" onclick="deleteSmartphone(${smartphone.id})">Delete</button></td>` +
        `<td class="btn"><button class="updateSmartphone" onclick="displayUpdateForm(${smartphone.id})">Update</button></td></tr>`;
}

function deleteSmartphone(id) {
    $.ajax({
        type: "DELETE",
        url: `http://localhost:8080/api/smartphones/${id}`,
        success: successHandler
    });
}

function displayUpdateForm(id) {
    // Load update.html content into a div or redirect to update.html
    window.location.href = `update.html?id=${id}`;
}

$(document).ready(function() {
    const params = new URLSearchParams(window.location.search);
    const id = params.get('id');
    if (id) {
        $.ajax({
            type: "GET",
            url: `http://localhost:8080/api/smartphones/${id}`,
            success: function (data) {
                $('#smartphone-id').val(data.id);
                $('#producer').val(data.producer);
                $('#model').val(data.model);
                $('#price').val(data.price);
            }
        });
    }
});

function updateSmartPhone() {
    let id = $('#smartphone-id').val();
    let producer = $('#producer').val();
    let model = $('#model').val();
    let price = $('#price').val();
    let updatedSmartphone = {
        producer: producer,
        model: model,
        price: price
    };

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        data: JSON.stringify(updatedSmartphone),
        url: `http://localhost:8080/api/smartphones/${id}`,
        success: function() {
            alert('Smartphone updated successfully!');
            window.location.href = "index.html"; // Redirect to the main page or the list page
        },
        error: function (xhr, status, error) {
            console.error('Update failed:', error);
        }
    });

    event.preventDefault();
}

