-- Health Clinic Database Schema

CREATE DATABASE IF NOT EXISTS health_clinic;
USE health_clinic;

-- Specialties Lookup Table
CREATE TABLE specialties (
    specialty_id INT PRIMARY KEY AUTO_INCREMENT,
    specialty_name VARCHAR(100) NOT NULL UNIQUE
);

-- Doctors Table
CREATE TABLE doctors (
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    specialty_id INT,
    contact VARCHAR(15),
    consultation_fee DECIMAL(10,2),
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (specialty_id) REFERENCES specialties(specialty_id)
);

-- Patients Table
CREATE TABLE patients (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    dob DATE,
    contact VARCHAR(15) UNIQUE,
    email VARCHAR(100) UNIQUE,
    address TEXT,
    blood_group VARCHAR(5),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Appointments Table
CREATE TABLE appointments (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    status ENUM('SCHEDULED','COMPLETED','CANCELLED') DEFAULT 'SCHEDULED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
);

-- Visits Table
CREATE TABLE visits (
    visit_id INT PRIMARY KEY AUTO_INCREMENT,
    appointment_id INT NOT NULL,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    visit_date DATE NOT NULL,
    diagnosis TEXT,
    notes TEXT,
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
);

-- Prescriptions Table
CREATE TABLE prescriptions (
    prescription_id INT PRIMARY KEY AUTO_INCREMENT,
    visit_id INT NOT NULL,
    medicine_name VARCHAR(100) NOT NULL,
    dosage VARCHAR(50),
    duration VARCHAR(50),
    FOREIGN KEY (visit_id) REFERENCES visits(visit_id)
);

-- Bills Table
CREATE TABLE bills (
    bill_id INT PRIMARY KEY AUTO_INCREMENT,
    visit_id INT NOT NULL,
    patient_id INT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    payment_status ENUM('PAID','UNPAID') DEFAULT 'UNPAID',
    payment_date DATE,
    payment_mode VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (visit_id) REFERENCES visits(visit_id),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);

-- Payment Transactions Table
CREATE TABLE payment_transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    bill_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    payment_date DATE NOT NULL,
    payment_mode VARCHAR(50),
    FOREIGN KEY (bill_id) REFERENCES bills(bill_id)
);

-- Appointment Audit Table
CREATE TABLE appointment_audit (
    audit_id INT PRIMARY KEY AUTO_INCREMENT,
    appointment_id INT,
    action VARCHAR(50),
    action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Audit Log Table
CREATE TABLE audit_log (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    table_name VARCHAR(50),
    action VARCHAR(20),
    user VARCHAR(50),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Indexes
CREATE INDEX idx_patient_contact ON patients(contact);
CREATE INDEX idx_appointment_date ON appointments(appointment_date);
CREATE INDEX idx_doctor_specialty ON doctors(specialty_id);
CREATE INDEX idx_visit_patient ON visits(patient_id);

-- Insert Sample Specialties
INSERT INTO specialties (specialty_name) VALUES 
('Cardiology'), ('Dermatology'), ('Pediatrics'), ('Orthopedics'), ('General Medicine');
