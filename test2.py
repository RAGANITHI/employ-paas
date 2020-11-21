from selenium import webdriver
import os
print(os.getcwd())
driver = webdriver.Firefox('/usr/local/bin/')
driver.get('http://inventwithpython.com')
