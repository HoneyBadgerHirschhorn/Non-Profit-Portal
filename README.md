# Non-Profit Portal

A Java Spring Boot web application for managing help requests, posts, replies and events for a non-profit organization.

## Current Features
- Home page with logo
- About page with sample mission statement
- User registration page (this saves into MySQL)
- Placeholder for faith-themed personality type quiz
- Ability for registered users to put in category-based help requests or create events
- Category-based help request listings
- Ability to respond to help request listings with posts
- Threaded replies to posts
- Events calendar (does not yet save to database)
  
## In Development
- Admin and user roles with different permissions
- Permission-based veiwing and involvement for different help categories
- Evaluation / Onboarding process to establish permissions needed for different categories
- Staff referral page (for sensitive requests that are not appropriate for the general community to know about)
- Subscription system to accessible categories
- Notication system for SMS/Email/etc. as part of the subscription system
- ** Brainstorming ** Checkout-based system for answering requests for record keeping. 
- Visual overhaul for a more modern feel
- Conflict resolution system (private communication / staff referral)

## Tech Stack
- **Backend:** Java 22, Spring Boot, Spring Data JPA, Hibernate
- **Frontend:** HTML, CSS, JavaScript 
- **Database:** MySQL (production), H2 (tests)
- **Build Tool:** Maven

## Getting Started

### Prerequisites
- Java 22 or later installed
- Maven installed
- MySQL server running

