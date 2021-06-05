<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:t="urn:ns">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes" encoding="UTF-8"/>

    <xsl:key name="specialty_ref" match="t:specialty" use="@id"/>
    <xsl:key name="services_ref" match="t:service" use="@id"/>

    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <link rel="stylesheet"
                      href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"/>
                <title>Tasks Table</title>
            </head>
            <body>
                <div class="container">
                    <h1 class="center-align">Doctors</h1>
                    <table class="table-responsive highlight">
                        <thead>
                            <tr>
                                <th>Number</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Age</th>
                                <th>Category</th>
                                <th>Specialty</th>
                                <th>Services</th>
                                <th>Prices</th>
                                <th>Vacation Start</th>
                                <th>Vacation End</th>
                                <th>On Vacation</th>
                            </tr>
                        </thead>
                        <tbody>
                            <xsl:for-each select="t:root/t:doctors/t:doctor">
                                <xsl:sort select="t:age" />
                                <tr>
                                    <td>
                                        <xsl:number value="position()" format="1." />
                                    </td>
                                    <td>
                                        <xsl:value-of select="t:first_name"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="t:last_name"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="t:age"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="t:category"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="key('specialty_ref', @specialty_ref)/t:name"/>
                                    </td>
                                    <td>
                                        <xsl:for-each select="key('services_ref', tokenize(@services_ref, '\s'))">
                                            <p><xsl:value-of select="t:name"/></p>
                                        </xsl:for-each>
                                    </td>
                                    <td>
                                        <xsl:for-each select="key('services_ref', tokenize(@services_ref, '\s'))">
                                            <p><xsl:value-of select="@price"/></p>
                                        </xsl:for-each>
                                    </td>
                                    <td>
                                        <xsl:value-of select="t:vacation_start"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="t:vacation_end"/>
                                    </td>
                                    <td>
                                        <xsl:choose>
                                            <xsl:when test="xs:date(t:vacation_start) &lt;= current-date() and current-date() &lt;= xs:date(t:vacation_end)">Yes</xsl:when>
                                            <xsl:otherwise>No</xsl:otherwise>
                                        </xsl:choose>
                                    </td>
                                </tr>
                            </xsl:for-each>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th>Doctors working</th>
                                <td><xsl:value-of select="count(//t:doctor[t:vacation_start &gt; current-date() or t:vacation_end &lt; current-date()])" /></td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>