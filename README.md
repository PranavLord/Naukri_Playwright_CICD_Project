**# Naukri_Playwright_CICD_Project**

This project automates end-to-end testing for the Naukri website using **Playwright**, integrated with **GitHub Actions** for Continuous Integration and Continuous Deployment (CI/CD).

## 🚀 Project Highlights

- ✅ Automated browser testing using **Playwright**
- 🔄 Seamless **CI/CD pipeline** with **GitHub Actions**
- 📄 Test reports generated automatically
- 🛠️ Modular test structure for maintainability and scalability

## 🧰 Tech Stack

- **Language:** TypeScript / JavaScript  
- **Automation Framework:** Playwright  
- **CI/CD:** GitHub Actions  
- **Reporting:** Playwright's built-in reporters  
- **Version Control:** Git + GitHub

## 📂 Project Structure

Naukri_Playwright_CICD_Project/
│
├── tests/ # Playwright test scripts
├── .github/workflows/ # CI/CD workflow files
├── playwright.config.ts # Configuration file
├── package.json # Project dependencies
├── build/ # (optional) Build output
├── target/ # (optional) Reports/output
└── README.md


## 🔧 Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/PranavLord/Naukri_Playwright_CICD_Project.git
   cd Naukri_Playwright_CICD_Project
Install dependencies


npm install
Run tests locally

npx playwright test
View reports

npx playwright show-report
🔄 CI/CD Integration
Every push to the main branch triggers the GitHub Actions pipeline to:

Install dependencies

Run Playwright tests

Generate test reports

📌 Notes
Ensure Playwright browsers are installed:


npx playwright install
Update test locators as the Naukri website UI changes to avoid flaky tests.

🙌 Contribution
Feel free to fork and open a pull request. For major changes, please open an issue first to discuss what you’d like to change.

👨‍💻 Developed By
Pranav
https://github.com/PranavLord

