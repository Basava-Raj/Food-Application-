<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Details</title>
    <link rel="stylesheet" href="checkout.css"> <!-- External CSS file -->
</head>
<body>
    <div class="checkout-container">
        <h1>Payment and Address Details</h1>

        <div class="payment-details">
            <h2>Payment Details</h2>
            <form action="CheckOutServlet" method="post">
                <label for="name">Full Name:</label>
                <input type="text" id="name" name="name" required>

                <label for="address">Delivery Address:</label>
                <textarea id="address" name="address" rows="4" required></textarea>

                <label for="payment-method">Payment Method:</label>
                <select id="payment-method" name="paymentMethod" required>
                    <option value="card">Credit/Debit Card</option>
                    <option value="upi">UPI</option>
                    <option value="netbanking">Net Banking</option>
                    <option value="cod">Cash on Delivery</option>
                </select>

                <div id="card-details" style="display: none;">
                    <label for="card">Card Number:</label>
                    <input type="text" id="card" name="card">

                    <label for="expiry">Expiry Date:</label>
                    <input type="month" id="expiry" name="expiry">

                    <label for="cvv">CVV:</label>
                    <input type="text" id="cvv" name="cvv">
                </div>

                <div id="upi-details" style="display: none;">
                    <label for="upi-id">UPI ID:</label>
                    <input type="text" id="upi-id" name="upiId">
                </div>

                <button type="submit">Confirm Order</button>
            </form>
        </div>
    </div>

    <script>
        const paymentMethod = document.getElementById('payment-method');
        const cardDetails = document.getElementById('card-details');
        const upiDetails = document.getElementById('upi-details');

        paymentMethod.addEventListener('change', function () {
            cardDetails.style.display = 'none';
            upiDetails.style.display = 'none';

            if (this.value === 'card') {
                cardDetails.style.display = 'block';
            } else if (this.value === 'upi') {
                upiDetails.style.display = 'block';
            }
        });
    </script>
</body>
</html>
