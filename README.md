# E-Commerce Android App

## Vision Statement

Our vision is to create a user-friendly e-commerce platform that provides customers with a seamless shopping experience. We aim to offer a wide range of products, intuitive navigation, secure transactions, and personalized recommendations to enhance customer satisfaction and loyalty.

## Feature: Amazon Cognito User Authentication and Authorization

### User Stories

1. As a customer, I want to create an account so that I can join the ECommerceApp
2. As a customer, I want to sign in securely to access my account and make purchases.


### Acceptance Criteria

1. User should be able to sign up with a valid email address and password.
2. User should receive a confirmation code via email for account verification during sign-up.
3. User should be able to sign in with their registered email address and password.
4. User authentication should be secure and protect user credentials from unauthorized access.
5. Admin should be able to define different user roles such as customer and administrator.
6. Certain features like order management and product reviews should be accessible only to authenticated users.

### Development Tasks

1. **Integration with Amazon Cognito SDK**:
    - Integrate the AWS SDK for Android into the project.
    - Configure the Amazon Cognito user pool and app client settings in the application.
    - Set up authentication and authorization handlers.

2. **Implement Sign-Up UI**:
    - Design and implement a sign-up screen with input fields for email address, password, and confirmation code.

3. **Implement Sign-In UI**:
    - Design and implement a sign-in screen with input fields for email address and password.

4. **Handle User Authentication**:
    - Implement logic to handle user sign-up using Amazon Cognito SDK.
    - Implement logic to handle user sign-in using Amazon Cognito SDK.

5. **Email Verification**:
    - Implement logic to handle email verification during the sign-up process.
    - Verify user's email address using the confirmation code sent by Amazon Cognito.

6. **Secure Token Storage**:
    - Implement a secure method for storing authentication tokens (ID token, access token, refresh token) locally on the device.

7. **Role-Based Access Control**:
    - Implement logic to assign different roles to users (e.g., customer, moderator, administrator).
    - Implement role-based access control to restrict access to certain features based on user roles.

8. **UI/UX Enhancements**:
    - Improve the user interface and experience for the sign-up and sign-in screens.
    - Implement error handling and feedback mechanisms to provide users with clear feedback during authentication processes.
