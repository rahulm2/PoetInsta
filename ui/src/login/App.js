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
						const reqBody = {
							username: values.email,
							password: values.password,
							'login-submit': 'Log In',
						};
						axios
							.post('/login', qs.stringify(reqBody), config)
							.then((response) => {
								NotificationManager.success('Success message', 'Login successful!!!', 3000);
								setSubmitting(false);
								window.location.href = '/';
							})
							.catch((error) => {
								NotificationManager.error('Error message', error.response.data.exception, 3000);
								setSubmitting(false);
							});
						setTimeout(() => {
							console.log('Logging in', values);
						}, 500);
					}}
					validationSchema={Yup.object().shape({
						email: Yup.string().email().required('Required'),
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
									Login
								</button>
								<span style={{ marginLeft: '5px' }}>
									New user? <a href="/registration.html">Register here</a>
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
