# Future expansion proposal

## Email or SMS booking notifications

Send a confirmation after a successful payment and a cancellation message if a booking or show is cancelled. This gives customers a receipt and helps them avoid travelling to a cancelled screening.

User, Booking, Show and Payment would be affected. A new Notification entity could record the recipient, channel, delivery status and sent time. Each booking would have many notifications, and each notification would belong to one booking. A notification service would call the chosen email or SMS provider after the booking transaction succeeds.

## Online payment gateway integration

Connect a payment provider supporting LKR. Customers could pay online and the backend could use a verified gateway result.

Payment and Booking would be affected. Payment could gain a gateway reference and verification fields without adding a new entity. If multiple gateway attempts need separate audit records, a PaymentAttempt entity could be added with a many-to-one relationship to Payment. A successful, verified callback would confirm the booking; duplicate callbacks would leave a completed payment unchanged.
