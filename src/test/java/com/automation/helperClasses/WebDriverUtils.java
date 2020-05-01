package com.automation.helperClasses;
//package com.automation.utilities;
//
//import java.io.File;
//import java.text.MessageFormat;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import org.apache.commons.io.FileUtils;
//import org.apache.log4j.Logger;
//import org.openqa.selenium.By;
//import org.openqa.selenium.ElementNotVisibleException;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.StaleElementReferenceException;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.remote.Augmenter;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class WebDriverUtils {
//
//	private WebDriver driver;
//	private Actions actions;
//	private static final String SEPARATOR = System.getProperty("file.separator");
//	protected static final Logger log = Logger.getLogger(WebDriverUtils.class);
//
//	public WebDriverUtils() {
//		if(LocalDriverManager.getWebDriver()!=null){
//			this.driver = LocalDriverManager.getWebDriver();
//
//		}
//		else if(LocalDriverManager.getAndroidDriver()!=null){
//			this.driver = LocalDriverManager.getAndroidDriver();
//
//		}else if(LocalDriverManager.getIOSDriver()!=null) {
//			this.driver = LocalDriverManager.getIOSDriver();
//		}
//		actions = new Actions(driver);
//
//	}
//
//	public WebDriver getWebDriver() {
//		return LocalDriverManager.getWebDriver();
//	}
//
//	public WebDriverUtils visit(String url) {
//		driver.get(url);
//		return this;
//	}
//
//	public WebElement findElement(By locator) {
//		return new WebDriverWait(driver, Config.getInstance().getWebDriverConfig().getWaitForTimeout())
//				.ignoring(StaleElementReferenceException.class)
//				.until(ExpectedConditions.presenceOfElementLocated(locator));
//	}
//	public WebElement findElement(WebElement element) {
//		return new WebDriverWait(driver, Config.getInstance().getWebDriverConfig().getWaitForTimeout())
//				.ignoring(StaleElementReferenceException.class)
//				.until(ExpectedConditions.visibilityOf(element));
//	}
//	public WebElement findElement(WebElement element,long timeout) {
//		return new WebDriverWait(driver, timeout)
//				.ignoring(StaleElementReferenceException.class)
//				.until(ExpectedConditions.visibilityOf(element));
//	}
//	public WebElement findElement(By locator, long timeout) {
//		return new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
//				.until(ExpectedConditions.presenceOfElementLocated(locator));
//	}
//
//	public List<WebElement> findElements(By locator) {
//		return driver.findElements(locator);
//	}
//
//	private boolean isElementPresent(By locator) {
//		return !findElements(locator).isEmpty();
//	}
//
//	public boolean isElementDisplayed(By locator) {
//
//		return isElementPresent(locator) && findElement(locator).isDisplayed();
//	}
//
//	public boolean isElementSelected(By locator) {
//
//		return isElementPresent(locator) && findElement(locator).isSelected();
//	}
//
//	public boolean isElementEnabled(By locator) {
//
//		return isElementPresent(locator) && findElement(locator).isEnabled();
//	}
//
//	public WebDriverUtils click(By locator) {
//		findElement(locator).click();
//		return this;
//	}
//	public WebDriverUtils click(WebElement element) {
//		findElement(element).click();
//		return this;
//	}
//
//	public WebDriverUtils clickByJS(By locator) {
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", findElement(locator));
//		return this;
//	}
//
//	public String getText(By locator) {
//
//		return findElement(locator).getText();
//	}
//
//	public String getTextByJS(By locator) {
//		WebElement webElement = findElement(locator);
//		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].text;", webElement);
//	}
//
//
//
//	public String getAttributeValue(By locator, String attribute) {
//		try {
//			return findElement(locator).getAttribute(attribute);
//		} catch (NoSuchElementException e) {
//			log.error("",e);
//		}
//		return null;
//	}
//
//	public List<String> getAttributeValues(By locator, String attribute) {
//		List<String> values=new ArrayList<>();
//		try {
//			List<WebElement> elements=findElements(locator);
//			for (WebElement ele :elements) {
//				values.add(  ele.getAttribute(attribute));
//			}
//		} catch (NoSuchElementException e) {
//			log.error("",e);
//		}
//		return values;
//	}
//	public WebDriverUtils type(By locator, String data) {
//		WebElement webElement = findElement(locator);
//		webElement.clear();
//		webElement.sendKeys(data);
//		return this;
//	}
//
//	public WebDriverUtils deleteAllCookies() {
//		driver.manage().deleteAllCookies();
//		return this;
//	}
//
//	public WebDriverUtils typeAndPressEnter(By locator, String data) {
//		WebElement webElement = findElement(locator);
//		webElement.clear();
//		webElement.sendKeys(data, Keys.RETURN);
//		return this;
//	}
//
//	public WebDriverUtils typeByJS(By locator, String data) {
//		WebElement webElement = findElement(locator);
//		webElement.clear();
//		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + data + "';", webElement);
//		return this;
//	}
//
//	public WebDriverUtils selectElement(By locator) {
//		if (!isElementSelected(locator)) {
//			click(locator);
//			sleep(1000);
//		}
//		return this;
//	}
//
//	public WebDriverUtils deselectElement(By locator) {
//		if (isElementSelected(locator)) {
//			click(locator);
//			sleep(1000);
//		}
//		return this;
//	}
//
//	public WebDriverUtils selectByJavaScript(By locator) {
//		if (!isElementSelected(locator)) {
//			clickByJS(locator);
//			sleep(1000);
//		}
//		return this;
//	}
//
//	public String getCurrentURL() {
//		return driver.getCurrentUrl();
//	}
//
//	public WebDriverUtils scrollToTop() {
//		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
//		return this;
//	}
//
//	public WebDriverUtils scrollToBottom() {
//		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
//		return this;
//	}
//
//	public WebDriverUtils scrollToOffset(int x, int y) {
//		((JavascriptExecutor) driver).executeScript("window.scrollTo(" + x + "," + (y - 100) + ")");
//		return this;
//	}
//
//	public WebDriverUtils scrollToElement(By locator) {
//		scrollToTop();
//		WebElement element = findElement(locator);
//		scrollToOffset(element.getLocation().x, element.getLocation().y);
//		return this;
//	}
//
//
//	public WebDriverUtils acceptAlert() {
//		boolean flag = false;
//		for (int i = 0; i < 5; i++) {
//			if (flag) {
//				break;
//			}
//			driver.switchTo().alert().accept();
//			flag = true;
//
//		}
//		return this;
//	}
//
//	public WebDriverUtils dismissAlert() {
//		boolean flag = false;
//		for (int i = 0; i < 5; i++) {
//			if (flag) {
//				break;
//			}
//			driver.switchTo().alert().dismiss();
//			flag = true;
//		}
//		return this;
//	}
//
//	public WebDriverUtils waitForElement(By locator, long timeOutInSeconds) {
//
//		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
//		return this;
//	}
//
//	public WebDriverUtils waitForElementUntil(ExpectedCondition<?> expectedCondition) {
//
//		new WebDriverWait(driver, 10).until(expectedCondition);
//		return this;
//	}
//
//	public WebDriverUtils waitForElementUntil(ExpectedCondition<?> expectedCondition, long timeOutInSeconds) {
//
//		new WebDriverWait(driver, timeOutInSeconds).until(expectedCondition);
//		return this;
//	}
//
//	public boolean switchToWindow(String title) {
//
//		String currentTab = driver.getWindowHandle();
//		ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
//		windows.remove(currentTab);
//		for (String tab : windows) {
//			driver.switchTo().window(tab);
//			if (driver.getTitle().equalsIgnoreCase(title)) {
//				return true;
//			}
//		}
//		driver.switchTo().window(currentTab);
//		return false;
//
//	}
//
//	public WebDriverUtils switchToDefaultContent() {
//		driver.switchTo().defaultContent();
//		return this;
//	}
//
//	public WebDriverUtils switchToFrame(By locator) {
//		driver.switchTo().frame(findElement(locator));
//		return this;
//	}
//
//	public WebDriverUtils switchToFrame(int index) {
//		driver.switchTo().frame(index);
//		return this;
//	}
//
//	public WebDriverUtils closePopup(By locator) {
//		try {
//			for(int i=0;i<=1;i++){
//				try {
//					waitForElementUntil(ExpectedConditions.visibilityOfElementLocated(locator), Config.getInstance().getWebDriverConfig().getWaitForTimeout());
//				}catch (Exception e) {
//				}}
//
//			List<WebElement> elements = findElements(locator);
//			for (WebElement element : elements) {
//				if (element.isDisplayed()) {
//					element.click();
//				}
//			}
//		} catch (NoSuchElementException | ElementNotVisibleException e) {
//			log.error("",e);
//		} 
//		waitForAjaxToComplete();
//		return this;
//	}
//
//	public WebDriverUtils mouseOver(By locator) {
//
//		actions.moveToElement(findElement(locator)).perform();
//		return this;
//
//	}
//
//	public WebDriverUtils mouseOver(WebElement locator) {
//
//		actions.moveToElement(findElement(locator)).perform();
//		return this;
//
//	}
//
//	public WebDriverUtils mouseOverAndClick(By locator) {
//
//		actions.moveToElement(findElement(locator)).click().perform();
//		return this;
//	}
//	public WebDriverUtils mouseOverAndClick(WebElement locator) {
//		actions.moveToElement(findElement(locator)).click().perform();
//		return this;
//	}
//
//
//	public WebDriverUtils doubleClick(By locator) {
//		actions.moveToElement(findElement(locator)).doubleClick().perform();
//		return this;
//	}
//
//	public boolean waitForAjaxToComplete() {
//		JavascriptExecutor je = (JavascriptExecutor) driver;
//		String jQueryActiveScript = "return jQuery.active";
//		long activeQueries = 100L;
//		int i = 0;
//		do {
//			i++;
//			try {
//				activeQueries = (long) je.executeScript(jQueryActiveScript);
//			} catch (Exception e) {
//
//				activeQueries = 100L;
//				i = i + 5;
//			}
//			sleep(1000);
//		} while (activeQueries > 0 && i < 30);
//		sleep(1000);
//		return activeQueries == 0L;
//	}
//
//	public boolean selectFromDropdown(By dropDownLocator, By optionsLocator, String value) {
//		click(dropDownLocator);
//		sleep(500);
//		for (WebElement element : findElements(optionsLocator)) {
//			if (value.equalsIgnoreCase(element.getText())) {
//				element.click();
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public boolean selectFromDropdown(By dropDownLocator, By optionsLocator, String attribute, String value) {
//		click(dropDownLocator);
//		sleep(500);
//		for (WebElement element : findElements(optionsLocator)) {
//			log.info(element.getAttribute(attribute));
//			if (value.equalsIgnoreCase(element.getAttribute(attribute))) {
//				element.click();
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public boolean scrollAndSelectFromDropdown(By dropDownLocator, By optionsLocator, String value) {
//		boolean isOptionFound = false;
//		click(dropDownLocator);
//		sleep(500);
//		List<WebElement> allOptions = findElements(optionsLocator);
//		for (WebElement element : allOptions) {
//			actions.moveToElement(element).perform();
//			String currentOptionText = element.getText().trim();
//			String currentOptionClass = element.getClass().toString();
//			if (value.trim().equals(currentOptionText)) {
//				if (!currentOptionClass.contains("SELECTED")) {
//					actions.click();
//					actions.perform();
//				}
//				isOptionFound = true;
//				break;
//			}
//
//		}
//		return isOptionFound;
//	}
//
//	public WebDriverUtils selectByVisibleText(By locator, String visibleText) {
//		Select dropdown = new Select(findElement(locator));
//		dropdown.selectByVisibleText(visibleText);
//		return this;
//	}
//
//	public List<WebElement> getSelectOptions(By locator) {
//		Select dropdown = new Select(findElement(locator));
//		return dropdown.getOptions();
//	}
//
//	public List<String> getSelectOptionsText(By locator) {
//		Select dropdown = new Select(findElement(locator));
//		ArrayList<String> data = new ArrayList<>();
//		for (WebElement element : dropdown.getOptions()) {
//			data.add(element.getText());
//		}
//		return data;
//	}
//
//	public List<String> getSelectOptionsAttribute(By locator, String attribute) {
//		Select dropdown = new Select(findElement(locator));
//		ArrayList<String> data = new ArrayList<>();
//		for (WebElement element : dropdown.getOptions()) {
//			data.add(element.getAttribute(attribute));
//		}
//		return data;
//	}
//
//	public WebDriverUtils selectByIndex(By locator, int index) {
//		Select dropdown = new Select(findElement(locator));
//		dropdown.selectByIndex(index);
//		return this;
//	}
//
//	public WebDriverUtils selectByValue(By locator, String value) {
//		Select dropdown = new Select(findElement(locator));
//		dropdown.selectByValue(value);
//		return this;
//	}
//
//	public WebDriverUtils deselectByVisibleText(By locator, String visibleText) {
//		Select dropdown = new Select(findElement(locator));
//		dropdown.deselectByVisibleText(visibleText);
//		return this;
//	}
//
//	public WebDriverUtils deselectByIndex(By locator, int index) {
//		Select dropdown = new Select(findElement(locator));
//		dropdown.deselectByIndex(index);
//		return this;
//	}
//
//	public WebDriverUtils deselectByValue(By locator, String value) {
//		Select dropdown = new Select(findElement(locator));
//		dropdown.deselectByValue(value);
//		return this;
//	}
//
//	public WebDriverUtils sleep(long miliseconds) {
//		try {
//			Thread.sleep(miliseconds);
//		} catch (InterruptedException e) {
//			Thread.currentThread().interrupt();
//			log.error("",e);
//		}
//		return this;
//	}
//
//	public WebDriverUtils onTestFailureScreenshot(String testCaseName, String folder) {
//
//		String path = "";
//		if (ExecutionSetup.isWebTest) {
//			for (int i = 0; i < 1; i++) {
//				try {
//					WebDriver augmentedDriver = new Augmenter().augment(driver);
//					File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
//					String folderName = folder;
//					path = folderName + SEPARATOR + "screenshots" + SEPARATOR + testCaseName + ".png";
//					FileUtils.copyFile(source, new File(path));
//				} catch (Exception e) {
//					continue;
//				}
//			}
//		}
//		return this;
//	}
//
//	public synchronized String takeScreenshot(String screenshotName) {
//		String screenshotPath = "";
//		String html = "";
//		if (ExecutionSetup.isScreenshot) {
//			for (int i = 0; i < 1; i++) {
//
//				try {
//					String unixTimestamp = String.valueOf((Instant.now().getEpochSecond()));
//					TakesScreenshot ts = (TakesScreenshot) driver;
//					File file = ts.getScreenshotAs(OutputType.FILE);
//					String folderName = "";
//					if (!ExecutionSetup.isCucumberTest) {
//						folderName = JunitListener.testOutputFolder;
//					} else {
//						folderName = CucumberRunner.testOutputFolder;
//					}
//
//					screenshotPath = folderName + SEPARATOR + "screenshots" + SEPARATOR;
//					new File(screenshotPath);
//					screenshotPath = screenshotPath + screenshotName + "_" + unixTimestamp + ".png";
//					FileUtils.copyFile(file, new File(screenshotPath));
//					String path = "screenshots" + SEPARATOR + screenshotName + "_" + unixTimestamp + ".png";
//					html = " <a target=_blank href=" + path.replaceAll(" ", "%20") + ">Screenshot  </a>";
//				} catch (Exception e) {
//					continue;
//				}
//
//			}
//		}
//		return html;
//
//	}
//
//	public By formatLocator(By by, final Object... substitutions) {
//		By returnBy;
//		String locator = by.toString().split(":")[0];
//		String locatorValue = by.toString().replaceAll(locator, "").replaceFirst(":", "").trim();
//
//		final Pattern typePattern = Pattern.compile("[=,]''[^\\]]*''");
//		final Matcher typeMatcher = typePattern.matcher(locatorValue);
//		if (typeMatcher.find()) {
//			locatorValue = MessageFormat.format(locatorValue, substitutions);
//		} else {
//			Pattern pattern = Pattern.compile("([{][0-9]+[}])");
//			Matcher matcher = pattern.matcher(locatorValue);
//			int count = 0;
//			while (matcher.find()) {
//				++count;
//			}
//			for (int i = 0; i < count; ++i) {
//				pattern = Pattern.compile("([{]" + i + "[}])");
//				matcher = pattern.matcher(locatorValue);
//				locatorValue = matcher.replaceAll(Matcher.quoteReplacement(substitutions[i].toString()));
//			}
//		}
//
//		Pattern replacePattern = Pattern.compile("[=,]'[^']*(['][\\w\\s!@#$%^&*-;:.\342\u201E\242/]*)+'");
//		final Matcher replaceMatcher = replacePattern.matcher(locatorValue);
//		while (replaceMatcher.find()) {
//			String matchValue = replaceMatcher.group();
//			matchValue = matchValue.replace("='", "=\"");
//			matchValue = matchValue.replace(",'", ",\"");
//			matchValue = matchValue.substring(0, matchValue.length() - 1) + "\"";
//			locatorValue = locatorValue.replace(replaceMatcher.group(), matchValue);
//		}
//		switch (locator) {
//		case "By.cssSelector":
//			returnBy = By.cssSelector(locatorValue);
//			break;
//		case "By.xpath":
//			returnBy = By.xpath(locatorValue);
//			break;
//		case "By.className":
//			returnBy = By.className(locatorValue);
//			break;
//		case "By.id":
//			returnBy = By.id(locatorValue);
//			break;
//		case "By.tagName":
//			returnBy = By.tagName(locatorValue);
//			break;
//		case "By.name":
//			returnBy = By.name(locatorValue);
//			break;
//		case "By.linkText":
//			returnBy = By.linkText(locatorValue);
//			break;
//		case "By.partialLinkText":
//			returnBy = By.partialLinkText(locatorValue);
//			break;
//		default:
//			throw new RuntimeException("invalid locator: " + by.toString());
//		}
//		return returnBy;
//	}
//
//	public WebDriverUtils waitForJSAndJQueryToLoad() {
//		WebDriverWait wait = new WebDriverWait(driver, 40);
//		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
//			@Override
//			public Boolean apply(WebDriver driver) {
//				try {
//					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
//				} catch (Exception e) {
//					return true;
//				}
//			}
//		};
//		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
//			@Override
//			public Boolean apply(WebDriver driver) {
//				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
//						.equals("complete");
//			}
//		};
//		return this;
//	}
//
//	public WebDriverUtils pressTab() {
//		Actions builder = new Actions(driver);
//		builder.sendKeys(Keys.TAB).perform();
//		return this;
//	}
//
//	public WebDriverUtils refresh() {
//		driver.navigate().refresh();
//		return this;
//	}
//
//	public WebDriverUtils navigateBack() {
//		driver.navigate().back();
//		return this;
//	}
//
//	public WebDriverUtils navigateForward() {
//		driver.navigate().forward();
//		return this;
//	}
//
//	public WebDriverUtils dragAndDrop(By from, By to) {
//
//		if (isElementPresent(from) && isElementPresent(to)) {
//
//			if (!driver.toString().contains("safari")) {
//				Actions builder = new Actions(driver);
//				builder.dragAndDrop(findElement(from), findElement(to)).perform();
//			} else {
//				WebElement locatorFrom = findElement(from);
//				WebElement locatorTo = findElement(to);
//				String xto = Integer.toString(locatorTo.getLocation().x);
//				String yto = Integer.toString(locatorTo.getLocation().y);
//				((JavascriptExecutor) driver).executeScript(
//						"function simulate(f,c,d,e){var b,a=null;for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\"?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a.altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick|mouse(?:down|up|over|move|out))$/}; "
//								+ "simulate(arguments[0],\"mousedown\",0,0); simulate(arguments[0],\"mousemove\",arguments[1],arguments[2]); simulate(arguments[0],\"mouseup\",arguments[1],arguments[2]); ",
//								locatorFrom, xto, yto);
//			}
//
//		}
//		return this;
//	}
//
//	public WebDriverUtils dragAndDropHtml5(By from, By to) {
//		String source = "#" + findElement(from).getAttribute("id");
//		String target = "#" + findElement(to).getAttribute("id");
//		if (!(source.isEmpty() || target.isEmpty())) {
//			jQuerify(driver);
//			String javaScript = "(function( $ ) {        $.fn.simulateDragDrop = function(options) {                return this.each(function() {                        new $.simulateDragDrop(this, options);                });        };        $.simulateDragDrop = function(elem, options) {                this.options = options;                this.simulateEvent(elem, options);        };        $.extend($.simulateDragDrop.prototype, {                simulateEvent: function(elem, options) {                        /*Simulating drag start*/                        var type = 'dragstart';                        var event = this.createEvent(type);                        this.dispatchEvent(elem, type, event);                        /*Simulating drop*/                        type = 'drop';                        var dropEvent = this.createEvent(type, {});                        dropEvent.dataTransfer = event.dataTransfer;                        this.dispatchEvent($(options.dropTarget)[0], type, dropEvent);                        /*Simulating drag end*/                        type = 'dragend';                        var dragEndEvent = this.createEvent(type, {});                        dragEndEvent.dataTransfer = event.dataTransfer;                        this.dispatchEvent(elem, type, dragEndEvent);                },                createEvent: function(type) {                        var event = document.createEvent(\"CustomEvent\");                        event.initCustomEvent(type, true, true, null);                        event.dataTransfer = {                                data: {                                },                                setData: function(type, val){                                        this.data[type] = val;                                },                                getData: function(type){                                        return this.data[type];                                }                        };                        return event;                },                dispatchEvent: function(elem, type, event) {                        if(elem.dispatchEvent) {                                elem.dispatchEvent(event);                        }else if( elem.fireEvent ) {                                elem.fireEvent(\"on\"+type, event);                        }                }        });})(jQuery);";
//			((JavascriptExecutor) driver).executeScript(
//					javaScript + "$('" + source + "')" + ".simulateDragDrop({ dropTarget: '" + target + "'});");
//		}
//		return this;
//	}
//
//	private static void jQuerify(WebDriver driver) {
//		String jQueryLoader = "(function(jqueryUrl, callback) {\n" + "    if (typeof jqueryUrl != 'string') {\n"
//				+ "        jqueryUrl = 'https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js';\n"
//				+ "    }\n" + "    if (typeof jQuery == 'undefined') {\n"
//				+ "        var script = document.createElement('script');\n"
//				+ "        var head = document.getElementsByTagName('head')[0];\n" + "        var done = false;\n"
//				+ "        script.onload = script.onreadystatechange = (function() {\n"
//				+ "            if (!done && (!this.readyState || this.readyState == 'loaded'\n"
//				+ "                    || this.readyState == 'complete')) {\n" + "                done = true;\n"
//				+ "                script.onload = script.onreadystatechange = null;\n"
//				+ "                head.removeChild(script);\n" + "                callback();\n" + "            }\n"
//				+ "        });\n" + "        script.src = jqueryUrl;\n" + "        head.appendChild(script);\n"
//				+ "    }\n" + "    else {\n" + "        callback();\n" + "    }\n"
//				+ "})(arguments[0], arguments[arguments.length - 1]);";
//		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeAsyncScript(jQueryLoader);
//
//	}
//
//}
//
