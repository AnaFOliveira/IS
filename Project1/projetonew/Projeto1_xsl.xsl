<?xml version="1.0" encoding="UTF-8"?>
<html xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xsl:version="1.0">
	<head>
		<title> CDs </title>
	</head>
	<body>
		<h1>Result</h1>
		<table border="2">
			<tr bgcolor="#9acd32">
				<td style="text-align:center">
					<b> Category </b>
				</td>
				<td style="text-align:center">
					<b> Title </b>
				</td>
				<td style="text-align:center">
					<b> Artist </b>
				</td>
				<td style="text-align:center">
					<b> Year </b>
				</td>
				<td style="text-align:center">
					<b> Rating </b>
				</td>
				<td style="text-align:center">
					<b> Number of reviews </b>
				</td>
				<td style="text-align:center">
					<b> Price </b>
				</td>
			</tr>
			<xsl:for-each select="/catalognew/category">
				<xsl:variable select="@id" name="random">
				</xsl:variable>
				<xsl:for-each select="vinylnew">
					<tr>
						<td style="text-align:center">
							<xsl:value-of select="$random" />
						</td>
						<td style="text-align:center">
							<xsl:value-of select="title" />
						</td>
						<td style="text-align:center">
							<xsl:value-of select="artist" />
						</td>
						<td style="text-align:center">
							<xsl:value-of select="year" />
						</td>
						<td style="text-align:center">
							<xsl:value-of select="rating" />
						</td>
						<td style="text-align:center">
							<xsl:value-of select="nreview" />
						</td>
						<td style="text-align:center">
							<xsl:value-of select="price" />
						</td>
					</tr>
				</xsl:for-each>
			</xsl:for-each>
		</table>
		<h1>Statistics</h1>

		<text>
			Number of artists:
			<xsl:value-of select="/catalognew/statistics/totalartists" />
		</text>
		<h3>Top Rate</h3>
		<table border="2">
			<tr bgcolor="#9acd32">
				<td style="text-align:center">
					<b> Artist </b>
				</td>
				<td style="text-align:center">
					<b> Album </b>
				</td>
				<td style="text-align:center">
					<b> Score </b>
				</td>
			</tr>
			<xsl:for-each select="/catalognew/statistics/rateartists">
				<tr>
					<td style="text-align:center">
						<xsl:value-of select="artistname" />
					</td>
					<td style="text-align:center">
						<xsl:value-of select="artistalbum" />
					</td>
					<td style="text-align:center">
						<xsl:value-of select="numberscore" />
					</td>
				</tr>
			</xsl:for-each>
		</table>
		<h1>Music</h1>
		<xsl:for-each select="/catalognew/category">
			<h3>
				<xsl:value-of select="@id" />
			</h3>
			<xsl:for-each select="vinylnew">
				<h4>
					<xsl:value-of select="title" />
				</h4>
				<table border="2">
					<tr bgcolor="#9acd32">
						<td style="text-align:center">
							<b> Song </b>
						</td>
						<td style="text-align:center">
							<b> Duration </b>
						</td>
						<td style="text-align:center">
							<b> Rating </b>
						</td>
						<td style="text-align:center">
							<b> Number of Reviews </b>
						</td>
						<td style="text-align:center">
							<b> Price </b>
						</td>
					</tr>
					<xsl:for-each select="musicnew">
						<tr>
							<td style="text-align:center">
								<xsl:value-of select="name" />
							</td>
							<td style="text-align:center">
								<xsl:value-of select="duration" />
							</td>
							<td style="text-align:center">
								<xsl:value-of select="srating" />
							</td>
							<td style="text-align:center">
								<xsl:value-of select="snreview" />
							</td>
							<td style="text-align:center">
								<xsl:value-of select="sprice" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</xsl:for-each>
		</xsl:for-each>

	</body>
</html>

