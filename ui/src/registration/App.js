import React from 'react';
import './App.css';
import Header from './Header';
import { Formik } from 'formik';
import * as Yup from 'yup';
import '../css/styles.css';
import axios from 'axios';
import qs from 'querystring';
import 'react-notifications/lib/notifications.css';
import { NotificationContainer, NotificationManager } from 'react-notifications';

const App = () => (
	<div class="parent">
		<Header />
		<div id="wrapper" style={{ overflowY: 'auto' }}>
			<div class="registration-form">
				<Formik
					initialValues={{ email: '', password: '' }}
					onSubmit={(values, { setSubmitting }) => {
						const config = {
							headers: {
								'Content-Type': 'application/x-www-form-urlencoded',
							},
						};
						axios
							.post('/registration', qs.stringify(values), config)
							.then((response) => {
								NotificationManager.success(
									'Success message',
									'You are successfully registered to PoetInsta'
								);
								setSubmitting(false);
								window.location.href = '/login';
							})
							.catch((error) => {
								NotificationManager.error('Error message', error.response.data, 3000);
								setSubmitting(false);
							});
						setTimeout(() => {
							console.log('Logging in', values);
						}, 500);
					}}
					validationSchema={Yup.object().shape({
						email: Yup.string().email().required('Required'),
						firstName: Yup.string().required('Required'),
						lastName: Yup.string().required('Required'),
						password: Yup.string()
							.required('No password provided.')
							.min(8, 'Password is too short - should be 8 chars minimum.')
							.matches(/(?=.*[0-9])/, 'Password must contain a number.'),
					})}
				>
					{(props) => {
						const { values, touched, errors, isSubmitting, handleChange, handleBlur, handleSubmit } = props;

						return (
							<form onSubmit={handleSubmit}>
								<label htmlFor="firstName">First Name</label>
								<input
									id="firstName"
									name="firstName"
									type="text"
									placeholder="Enter your first name"
									value={values.firstName}
									onChange={handleChange}
									onBlur={handleBlur}
									className={errors.firstName && touched.firstName && 'error'}
								/>
								{errors.firstName && touched.firstName && (
									<div className="input-feedback">{errors.firstName}</div>
								)}
								<label htmlFor="lastName">Last Name</label>
								<input
									id="lastName"
									name="lastName"
									type="text"
									placeholder="Enter your last name"
									value={values.lastName}
									onChange={handleChange}
									onBlur={handleBlur}
									className={errors.lastName && touched.lastName && 'error'}
								/>
								{errors.lastName && touched.lastName && (
									<div className="input-feedback">{errors.lastName}</div>
								)}
								<label htmlFor="email">Email</label>
								<input
									id="email"
									name="email"
									type="text"
									placeholder="Enter your email"
									value={values.email}
									onChange={handleChange}
									onBlur={handleBlur}
									className={errors.email && touched.email && 'error'}
								/>
								{errors.email && touched.email && <div className="input-feedback">{errors.email}</div>}
								<label htmlFor="password">Password</label>
								<input
									id="password"
									name="password"
									type="password"
									placeholder="Enter your password"
									value={values.password}
									onChange={handleChange}
									onBlur={handleBlur}
									className={errors.password && touched.password && 'error'}
								/>
								{errors.password && touched.password && (
									<div className="input-feedback">{errors.password}</div>
								)}
								<button type="submit" disabled={isSubmitting}>
									Register
								</button>
								<span style={{ marginLeft: '5px' }}>
									Already registered? <a href="/login.html">Login here</a>
								</span>
							</form>
						);
					}}
				</Formik>
				<NotificationContainer />
			</div>
		</div>
	</div>
);

export default App;
