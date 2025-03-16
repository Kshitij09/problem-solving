<p>You are given a string <code>s</code> and an integer <code>k</code>.</p>

<p>In one operation, you can replace the character at any position with the next or previous letter in the alphabet (wrapping around so that <code>'a'</code> is after <code>'z'</code>). For example, replacing <code>'a'</code> with the next letter results in <code>'b'</code>, and replacing <code>'a'</code> with the previous letter results in <code>'z'</code>. Similarly, replacing <code>'z'</code> with the next letter results in <code>'a'</code>, and replacing <code>'z'</code> with the previous letter results in <code>'y'</code>.</p>

<p>Return the length of the <strong>longest <span data-keyword="palindrome-string">palindromic</span> <span data-keyword="subsequence-string-nonempty">subsequence</span></strong> of <code>s</code> that can be obtained after performing <strong>at most</strong> <code>k</code> operations.</p>

<p>&nbsp;</p> 
<p><strong class="example">Example 1:</strong></p>

<div class="example-block"> 
 <p><strong>Input:</strong> <span class="example-io">s = "abced", k = 2</span></p> 
</div>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul> 
 <li>Replace <code>s[1]</code> with the next letter, and <code>s</code> becomes <code>"acced"</code>.</li> 
 <li>Replace <code>s[4]</code> with the previous letter, and <code>s</code> becomes <code>"accec"</code>.</li> 
</ul>

<p>The subsequence <code>"ccc"</code> forms a palindrome of length 3, which is the maximum.</p>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block"> 
 <p><strong>Input:</strong> <span class="example-io">s = "</span>aaazzz<span class="example-io">", k = 4</span></p> 
</div>

<p><strong>Output:</strong> 6</p>

<p><strong>Explanation:</strong></p>

<ul> 
 <li>Replace <code>s[0]</code> with the previous letter, and <code>s</code> becomes <code>"zaazzz"</code>.</li> 
 <li>Replace <code>s[4]</code> with the next letter, and <code>s</code> becomes <code>"zaazaz"</code>.</li> 
 <li>Replace <code>s[3]</code> with the next letter, and <code>s</code> becomes <code>"zaaaaz"</code>.</li> 
</ul>

<p>The entire string forms a palindrome of length 6.</p>

<p>&nbsp;</p> 
<p><strong>Constraints:</strong></p>

<ul> 
 <li><code>1 &lt;= s.length &lt;= 200</code></li> 
 <li><code>1 &lt;= k &lt;= 200</code></li> 
 <li><code>s</code> consists of only lowercase English letters.</li> 
</ul>

<div><div>Related Topics</div><div><li>String</li><li>Dynamic Programming</li></div></div><br><div><li>👍 84</li><li>👎 12</li></div>